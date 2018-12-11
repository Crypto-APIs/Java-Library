package com.cryptoapis.utils.rest;

import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class WebServices {
    private static Logger logger = Logger.getLogger(WebServices.class.getName());

    public static String formatUrl(String url, EndpointConfig endpointConfig, String endpoint) {
        return MessageFormat.format(url,
                endpointConfig.getVersion(),
                endpointConfig.getBlockchain(),
                endpointConfig.getNetwork(),
                endpoint);
    }

    public static String formatUrlExchanges(String url, EndpointConfig endpointConfig, String endpoint) {
        return MessageFormat.format(url,
                endpointConfig.getVersion(),
                endpoint);
    }

    public static ApiResponse httpsRequest(String url, String httpRequestType, EndpointConfig endpointConfig, String body) {

        URL resource;
        try {
            resource = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }

        HttpsURLConnection conn;
        DataOutputStream wr = null;
        try {
            conn = (HttpsURLConnection) resource.openConnection();
            conn.setRequestMethod(httpRequestType);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.addRequestProperty("Content-Encoding", "gzip");

            if (endpointConfig != null) {
                conn.setRequestProperty("x-api-key", endpointConfig.getApiKey());
            }
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent", StringUtils.EMPTY);

            if (httpRequestType.equals(HttpsRequestsEnum.POST.name())) {
                try {
                    wr = new DataOutputStream(conn.getOutputStream());
                } catch (Exception e) {
                    return null;
                }
                if (body != null)
                    wr.writeBytes(body);
                wr.flush();
            }
            if (wr != null) {
                wr.close();
            }
        } catch (IOException e) {
            return null;
        }

        try {
            return httpsResponse(conn, wr, endpointConfig);

        } catch (IOException e) {
            return null;
        }
    }

    private static ApiResponse httpsResponse(HttpsURLConnection conn, DataOutputStream wr, EndpointConfig endpointConfig) throws IOException {
        BufferedReader in = null;
        ApiResponse apiResponse = new ApiResponse();

        try {
            int responseCode = 0;
            try {
                responseCode = conn.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            apiResponse.setStatusCode(responseCode);

            if (responseCode == 200 || responseCode == 201) {
                try {
                    in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                apiResponse.setResponse(response.toString());
                return apiResponse;
            } else {
                BufferedReader errorIn = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = errorIn.readLine()) != null) {
                    response.append(inputLine);
                }

                errorIn.close();

                ApiError apiError = Utils.convertToCustomClass(response.toString(), ApiError.class, endpointConfig);
                if (apiError != null) {
                    apiError.setStatusCode(responseCode);
                    apiResponse.setResponse(apiError.toString());
                    return apiResponse;
                }

                apiResponse.setResponse(response.toString());
                return apiResponse;
            }
        } catch (IOException e) {
            throw e;

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }

            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                    //  logger.error(e);
                }
            }
        }
    }
}
