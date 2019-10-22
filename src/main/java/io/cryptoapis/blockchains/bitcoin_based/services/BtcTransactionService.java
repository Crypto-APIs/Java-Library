package io.cryptoapis.blockchains.bitcoin_based.services;

import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.CreateHDWalletTransaction;
import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.CreateTransaction;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

import java.util.List;

public class BtcTransactionService extends TransactionService {

    public BtcTransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    public ApiResponse createTx(List<CreateTransaction.Inputs> inputs, List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime, Boolean replaceable, String data) {
        CreateTransaction createTransaction = CreateTransaction.btcCreateTransaction(inputs, outputs, fee, locktime, replaceable, data);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "create"), HttpsRequestsEnum.POST.name(),
                endpointConfig, createTransaction.toString());
    }

    public ApiResponse newTxWithHDWallet(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                         List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                         Boolean replaceable, String data) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "hdwallet"), HttpsRequestsEnum.POST.name(), endpointConfig,
                CreateHDWalletTransaction.createBtc(walletName, password, inputs, outputs, fee, locktime, replaceable, data).toString());
    }
}
