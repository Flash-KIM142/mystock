package com.example.mystock.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
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
        String latestDate = timeSeries.keySet().stream().max(String::compareTo).orElseThrow();
        StockData stockData = timeSeries.get(latestDate);

        return String.format("Open: %s\nHigh: %s\nLow: %s\nClose: %s\nVolume: %s",
                stockData.getOpen(), stockData.getHigh(), stockData.getLow(),
                stockData.getClose(), stockData.getVolume());
    }
}
