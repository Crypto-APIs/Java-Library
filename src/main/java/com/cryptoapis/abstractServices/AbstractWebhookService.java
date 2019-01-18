package com.cryptoapis.abstractServices;

import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.models.Webhook;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.enums.WebhookEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWebhookService extends AbstractServicesConfig {
    protected AbstractWebhookService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    public Pair<Webhook, ApiError> createNewBlockWh(String webhookUrl) {
        Webhook wh = createWebhook(WebhookEnum.NEW_BLOCK.name(), webhookUrl);

        return broadcastWebhook(wh);
    }

    public Pair<Webhook, ApiError> createAddressWh(String webhookUrl, String address) {
        Webhook wh = createWebhook(WebhookEnum.ADDRESS.name(), webhookUrl);
        if (wh != null) {
            wh.setAddress(address);
            return broadcastWebhook(wh);
        }

        return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("Could not create webhook", 13), ApiError.class, null));
    }


    public Pair<Webhook, ApiError> createConfirmedTxWh(String webhookUrl, int confirmations, String transaction) {
        Webhook wh = createWebhook(WebhookEnum.CONFIRMED_TX.name(), webhookUrl);
        if (wh != null) {
            wh.setConfirmations(confirmations);
            wh.setTransaction(transaction);

            return broadcastWebhook(wh);
        }

        return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("Could not create webhook", 13), ApiError.class, null));
    }

    public Pair<Webhook, ApiError> createUnconfirmedTxWh(String webhookUrl) {
        Webhook wh = createWebhook(WebhookEnum.UNCONFIRMED_TX.name(), webhookUrl);

        return broadcastWebhook(wh);
    }

    public Pair<Webhook, ApiError> createTokenWh(String webhookUrl, String address) {
        Webhook wh = createWebhook(WebhookEnum.TOKEN.name(), webhookUrl);
        if (wh != null) {
            wh.setAddress(address);
            return broadcastWebhook(wh);
        }

        return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("Could not create webhook", 13), ApiError.class, null));
    }


    public Pair<String, ApiError> deleteWebhook(String whUuid) {
        return Utils.deleteUnit(whUuid, url, endpointConfig);
    }

    public Pair<List<Webhook>, ApiError> listWebhooks() throws JSONException {

        return getWebhooks();
    }

    private Pair<List<Webhook>, ApiError> getWebhooks() throws JSONException {
        ApiResponse whs = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        List<Webhook> resultArray = new ArrayList<>();
        if (whs != null) {
            JSONObject jsonObject = new JSONObject(whs.getResponse());
            if (whs.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("webhooks")) {
                result = jsonObject.getString("webhooks");
            }

            if (result != null) {
                JSONArray whsArray = new JSONArray(result);

                for (int idx = 0; idx < whsArray.length(); idx++) {
                    Webhook webhook = Utils.convertToCustomClass(whsArray.get(idx).toString(), Webhook.class, endpointConfig);
                    if (webhook != null) {
                        resultArray.add(webhook);
                    }
                }
            }
        }
        return new Pair<>(resultArray, null);
    }

    private Webhook createWebhook(String event, String webhookUrl) {
        Webhook webhook = new Webhook();
        webhook.setEvent(event);
        webhook.setUrl(webhookUrl);

        return webhook;
    }

    private Pair<Webhook, ApiError> broadcastWebhook(Webhook wh) {
        if (wh == null) {
            return null;
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, wh.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), Webhook.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }
}
