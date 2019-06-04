package com.cryptoapis.abstractServices;

import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.common_models.Webhook;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.enums.WebhookEnum;
import com.cryptoapis.utils.rest.WebServices;
import org.apache.commons.lang.StringUtils;

public abstract class AbstractWebhookService extends AbstractServicesConfig {
    protected AbstractWebhookService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    protected ApiResponse createNewBlockWh(String webhookUrl) {
        Webhook wh = Webhook.createNewBlock(WebhookEnum.NEW_BLOCK.name(), webhookUrl);

        return broadcastWebhook(wh);
    }

    protected ApiResponse createAddressWh(String webhookUrl, String address, int confirmations) {
        Webhook wh = Webhook.createAddress(WebhookEnum.ADDRESS.name(), webhookUrl, address, confirmations);

        return broadcastWebhook(wh);
    }

    protected ApiResponse createConfirmedTxWh(String webhookUrl, String transaction, int confirmations) {
        Webhook wh = Webhook.createConfirmed(WebhookEnum.CONFIRMED_TX.name(), webhookUrl, transaction, confirmations);

        return broadcastWebhook(wh);
    }

    protected ApiResponse createTransactionConfirmationsWh(String webhookUrl, String address, int confirmations) {
        Webhook wh = Webhook.createAddress(WebhookEnum.TRANSACTION_CONFIRMATIONS.name(), webhookUrl, address, confirmations);

        return broadcastWebhook(wh);
    }

    protected ApiResponse deleteWebhook(String whUuid) {
        return Utils.deleteUnit(whUuid, url, endpointConfig);
    }

    protected ApiResponse listWebhooks() {
        return getWebhooks();
    }

    private ApiResponse getWebhooks() {
         return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    protected ApiResponse broadcastWebhook(Webhook wh) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, wh.toString());
    }
}
