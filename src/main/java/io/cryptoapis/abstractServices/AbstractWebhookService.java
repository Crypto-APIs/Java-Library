package io.cryptoapis.abstractServices;

import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.common_models.Webhook;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.enums.WebhookEnum;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public abstract class AbstractWebhookService extends AbstractServicesConfig {

    private static final String DELETE_ALL_URL = "all";

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
        String endpoint = String.format("/%s", whUuid);

        return Utils.deleteUnit(endpoint, url, endpointConfig);
    }

    protected ApiResponse deleteAllWebhooks() {
        String endpoint = String.format("/%s", DELETE_ALL_URL);

        return Utils.deleteUnit(endpoint, url, endpointConfig);
    }

    protected ApiResponse listWebhooks(Map<String, String> params) {
        return getWebhooks(params);
    }

    private ApiResponse getWebhooks(Map<String, String> params) {
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }
        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    protected ApiResponse broadcastWebhook(Webhook wh) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, wh.toString());
    }
}
