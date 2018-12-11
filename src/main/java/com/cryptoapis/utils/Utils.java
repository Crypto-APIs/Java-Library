package com.cryptoapis.utils;

import com.cryptoapis.blockchains.bitcoin.models.BtcAddress.BtcAddressTransactions;
import com.cryptoapis.blockchains.ethereum.models.EthAddress.EthAddressTransactions;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.enums.EnumOptions;
import com.cryptoapis.utils.rest.WebServices;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class Utils {
    protected static Logger logger = Logger.getLogger(Utils.class.getName());

    public static Pair<String, ApiError> deleteUnit(String data, String url, EndpointConfig endpointConfig) {
        String endpoint = String.format("%s", data);
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.DELETE.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public static ApiError checkForError(ApiResponse res) {
        if (res != null && res.getStatusCode() >= 400) {
            return Utils.convertToCustomClass(res.getResponse(), ApiError.class, null);
        }
        return null;
    }

    public static <T> T convertToCustomClass(String data, Class<?> clazz, EndpointConfig endpointConfig) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            if (clazz == ApiError.class || clazz == BtcAddressTransactions.class || clazz == EthAddressTransactions.class) {
                return mapper.readValue(data, mapper.getTypeFactory().constructType(clazz));
            }

            Field field;
            final String property = "rawJSON";
            try {
                field = clazz.getSuperclass().getDeclaredField(property);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                logger.error(e);
                return null;
            }
            T obj = mapper.readValue(data, mapper.getTypeFactory().constructType(clazz));
            try {
                if (endpointConfig.getIncludeRawJSON()) {
                    field.set(obj, data);
                } else {
                    field.set(obj, StringUtils.EMPTY);
                }
            } catch (IllegalAccessException e) {
                logger.error(e);
                return null;
            }
            return obj;
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    public static Pair<String, ApiError> setQueryParams(Map<String, String> params) {
        URIBuilder builder = new URIBuilder();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                ApiError apiError = Utils.checkQueryParams(param.getKey().toLowerCase());
                if (apiError != null) {
                    return new Pair<>(null, apiError);
                }
                builder.setParameter(param.getKey(), param.getValue());
            }
        }

        URI uri;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 1), ApiError.class, null));
        }

        if (uri == null) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("Try again later", 1), ApiError.class, null));
        }
        return new Pair<>(uri.toString(), null);
    }

    private static ApiError checkQueryParams(String query) {
        for (EnumOptions.BTCTxsQueryParams q : EnumOptions.BTCTxsQueryParams.values()) {
            if (query.equals(q.toString())) {
                return null;
            }
        }

        if (!EnumUtils.isValidEnum(EnumOptions.QueryParams.class, query)) {
            return Utils.convertToCustomClass(setError("Please provide a proper query, refer to the documentation for more information", 14), ApiError.class, null);
        }

        return null;
    }

    public static String setError(String message, int code) {
        ApiError apiError = new ApiError();
        ApiError.Meta meta = new ApiError.Meta();
        ApiError.Meta.Error error = new ApiError.Meta.Error();
        error.setMessage(message);
        error.setCode(code);
        meta.setError(error);
        apiError.setMeta(meta);
        apiError.setStatusCode(400);

        return apiError.toString();
    }
}
