package io.cryptoapis.blockchains.bitcoin_based.services;

import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.CreateTransaction;
import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.HdWalletTransactionSize;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

import java.util.List;

public class BtcWalletService extends WalletService {

    public BtcWalletService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    public ApiResponse getHdWalletTransactionSize(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                                  List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                                  Boolean replaceable, String data) {
        String endpoint = "/hd/txs/size";

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                HdWalletTransactionSize.getHdWalletTransactionSizeBtc(walletName, password, inputs, outputs, fee, locktime, replaceable, data).toString());
    }
}
