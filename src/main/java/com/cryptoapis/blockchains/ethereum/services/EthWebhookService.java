package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.abstractServices.AbstractWebhookService;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.common_models.Webhook;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.WebhookEnum;


public class EthWebhookService extends AbstractWebhookService {
    private static final String PATH = "/{0}/bc/{1}/{2}/hooks/{3}";

    public EthWebhookService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    public ApiResponse createNewBlockWh(String webhookUrl) {
        return super.createNewBlockWh(webhookUrl);
    }

    @Override
    public ApiResponse createAddressWh(String webhookUrl, String address, int confirmations) {
        return super.createAddressWh(webhookUrl, address, confirmations);
    }

    @Override
    public ApiResponse createConfirmedTxWh(String webhookUrl, String transaction, int confirmations) {
        return super.createConfirmedTxWh(webhookUrl, transaction, confirmations);
    }

    @Override
    public ApiResponse createTransactionConfirmationsWh(String webhookUrl, String address, int confirmations) {
        return super.createTransactionConfirmationsWh(webhookUrl, address, confirmations);
    }

    public ApiResponse createTokenWh(String webhookUrl, String address, int confirmations) {
        Webhook wh = Webhook.createToken(WebhookEnum.TOKEN.name(), webhookUrl, address, confirmations);
        return super.broadcastWebhook(wh);
    }

    public ApiResponse createTXPoolWh(String webhookUrl, String address) {
        Webhook wh = Webhook.createTXPool(WebhookEnum.TXPOOL.name(), webhookUrl, address);
        return super.broadcastWebhook(wh);
    }

    @Override
    public ApiResponse deleteWebhook(String whUuid) {
        return super.deleteWebhook(whUuid);
    }

    @Override
    public ApiResponse listWebhooks() {
        return super.listWebhooks();
    }
}
