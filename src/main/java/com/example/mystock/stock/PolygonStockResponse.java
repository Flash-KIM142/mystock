package com.example.mystock.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Getter
@Slf4j
public class PolygonStockResponse {

    @JsonProperty("ticker")
    private String ticker;
    @JsonProperty("results")
    private List<Result> results;

    @Getter
    public static class Result {
        @JsonProperty("v")
        private BigDecimal v;
        @JsonProperty("o")
        private BigDecimal o;
        @JsonProperty("c")
        private BigDecimal c;
        @JsonProperty("h")
        private BigDecimal h;
        @JsonProperty("l")
        private BigDecimal l;
        @JsonProperty("t")
        private long t;
    }

    public BigDecimal getPrevClose() {
        Result prev = results.get(0);
        return prev.c;
    }

    public String getPrevStockDescription() {
        Result prev = results.get(0);
        return String.format("Previous Time: %s\nOpen: %s\nClose: %s\nHigh: %s\nLow: %s\nVolume: %s",
                unixToDate(prev.t), prev.o, prev.c, prev.h, prev.l, prev.v);
    }

    private String unixToDate(long unixMilliSeconds) {
        Date date = new Date(unixMilliSeconds);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return jdf.format(date);
    }
}
