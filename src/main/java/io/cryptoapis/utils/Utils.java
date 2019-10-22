package io.cryptoapis.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.enums.QueryParams;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang3.EnumUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class Utils {

    public static ApiResponse sendListRequest(String endpoint, Map<String, String> params, String url, EndpointConfig endpointConfig) {
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrlExchanges(url.concat(pair.getKey()), endpointConfig, endpoint), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);
    }

    public static ApiResponse deleteUnit(String data, String url, EndpointConfig endpointConfig) {
        String endpoint = String.format("%s", data);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.DELETE.name(), endpointConfig, null);
    }

    public static ApiError convertToApiError(String data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(data, mapper.getTypeFactory().constructType(ApiError.class));
        } catch (IOException e) {
            return null;
        }
    }

    public static ApiResponse setApiResponse(ApiError apiError) {
        ApiResponse response = new ApiResponse();
        response.setStatusCode(apiError.getStatusCode());
        response.setResponse(apiError.getMeta().getError().toString());
        return response;
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
            return new Pair<>(null, Utils.convertToApiError(Utils.setError(e.getMessage(), 1)));
        }

        if (uri == null) {
            return new Pair<>(null, Utils.convertToApiError(Utils.setError("Try again later", 1)));
        }
        return new Pair<>(uri.toString(), null);
    }

    private static ApiError checkQueryParams(String query) {
        if (!EnumUtils.isValidEnum(QueryParams.class, query)) {
            return Utils.convertToApiError(setError("Please provide a proper query, refer to the documentation for more information", 14));
        }

        return null;
    }

    private static String setError(String message, int code) {
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
