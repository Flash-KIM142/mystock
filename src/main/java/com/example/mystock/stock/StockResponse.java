package com.example.mystock.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Slf4j
public class StockResponse {

    @JsonProperty("Meta Data")
    private MetaData metaData;
    @JsonProperty("Time Series (5min)")
    private Map<String, StockData> timeSeries;

    @Getter
    public static class MetaData {
        @JsonProperty("2. Symbol")
        private String symbol;
        @JsonProperty("3. Last Refreshed")
        private String lastRefreshed;
    }

    @Getter
    public static class StockData {
        @JsonProperty("1. open")
        private String open;
        @JsonProperty("2. high")
        private String high;
        @JsonProperty("3. low")
        private String low;
        @JsonProperty("4. close")
        private String close;
        @JsonProperty("5. volume")
        private String volume;
    }

    public String buildLatestDailyStockDescription() {
        if (timeSeries == null || timeSeries.isEmpty()) {
            log.error("TimeSeries data is null or empty");
            return "No stock data available at the moment.";
        }

        log.info("TimeSeries keys: " + timeSeries.keySet());
        String latestDate = timeSeries.keySet().stream().max(String::compareTo).orElseThrow();
        StockData stockData = timeSeries.get(latestDate);

        return String.format("Time: %s\nOpen: %s\nHigh: %s\nLow: %s\nClose: %s\nVolume: %s",
                latestDate, stockData.getOpen(), stockData.getHigh(),
                stockData.getLow(), stockData.getClose(), stockData.getVolume());
    }
}
