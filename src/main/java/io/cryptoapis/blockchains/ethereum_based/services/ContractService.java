package io.cryptoapis.blockchains.ethereum_based.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.blockchains.ethereum_based.models.Contract;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;

public class ContractService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/contracts/{3}";

    public ContractService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse estimateGasSC() {
      return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "gas"), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse deploySC(String privateKey, String fromAddress, BigInteger gasPrice, BigInteger gasLimit, String byteCode) {
        Contract contract = Contract.setData(privateKey, fromAddress, gasPrice, gasLimit, byteCode);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, contract.toString());
    }
}
