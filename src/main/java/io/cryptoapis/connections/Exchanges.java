package io.cryptoapis.connections;

import io.cryptoapis.exchanges.services.*;
import io.cryptoapis.utils.config.EndpointConfig;

public class Exchanges extends ConnectionConstructor {

    private MetadataService metadataService;
    private ExchangeRatesService exchangeRatesService;
    private OHLCVService ohlcvService;
    private TradesService tradesService;
    private OrderBookService orderBookService;

    public Exchanges(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    public MetadataService getMetadataService() {
        return metadataService;
    }

    public ExchangeRatesService getExchangeRatesService() {
        return exchangeRatesService;
    }

    public OHLCVService getOhlcvService() {
        return ohlcvService;
    }

    public TradesService getTradesService() {
        return tradesService;
    }

    public OrderBookService getOrderBookService() {
        return orderBookService;
    }


    private void initServices(EndpointConfig endpointConfig) {
        try {
            this.metadataService = getConstructor(MetadataService.class).newInstance(endpointConfig);
            this.exchangeRatesService = getConstructor(ExchangeRatesService.class).newInstance(endpointConfig);
            this.ohlcvService = getConstructor(OHLCVService.class).newInstance(endpointConfig);
            this.tradesService = getConstructor(TradesService.class).newInstance(endpointConfig);
            this.orderBookService = getConstructor(OrderBookService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
