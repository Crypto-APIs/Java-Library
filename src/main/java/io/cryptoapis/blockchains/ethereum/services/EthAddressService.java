package io.cryptoapis.blockchains.ethereum.services;

import io.cryptoapis.blockchains.ethereum.models.EthAccount;
import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import java.util.Map;

public class EthAddressService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/{3}";

    public EthAddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    private static final String ADDRESS = "address";

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getAddressInfo(String address) {
        String endpoint = String.format("%s/%s", ADDRESS, address);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse generateNewAddress() {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, ADDRESS), HttpsRequestsEnum.POST.name(), endpointConfig, StringUtils.EMPTY);
    }

    public ApiResponse generateAccount(String password) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "account"), HttpsRequestsEnum.POST.name(), endpointConfig,
                EthAccount.createAccount(password).toString());
    }

    public ApiResponse getTxsByAddress(String address, Map<String, String> params) {
        String endpoint = String.format("%s/%s/transactions", ADDRESS, address);
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getNonce(String address) {
        String endpoint = String.format("%s/%s/nonce", ADDRESS, address);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
