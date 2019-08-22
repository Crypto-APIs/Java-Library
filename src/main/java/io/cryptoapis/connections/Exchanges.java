package io.cryptoapis.connections;

import io.cryptoapis.exchanges.services.*;
import io.cryptoapis.exchanges.services.ExchangeRatesService;
import io.cryptoapis.exchanges.services.MetadataService;
import io.cryptoapis.exchanges.services.QuotesService;
import io.cryptoapis.exchanges.services.TradesService;
import io.cryptoapis.utils.config.EndpointConfig;
import org.apache.log4j.Logger;

public class Exchanges extends ConnectionConstructor {
    private static final Logger logger = Logger.getLogger(Exchanges.class);

    private MetadataService metadataService;
    private ExchangeRatesService exchangeRatesService;
    private OHLCVService ohlcvService;
    private TradesService tradesService;
    private QuotesService quotesService;

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

    public QuotesService getQuotesService() {
        return quotesService;
    }

    private void initServices(EndpointConfig endpointConfig) {
        try {
            this.metadataService = getConstructor(MetadataService.class).newInstance(endpointConfig);
            this.exchangeRatesService = getConstructor(ExchangeRatesService.class).newInstance(endpointConfig);
            this.ohlcvService = getConstructor(OHLCVService.class).newInstance(endpointConfig);
            this.tradesService = getConstructor(TradesService.class).newInstance(endpointConfig);
            this.quotesService = getConstructor(QuotesService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
