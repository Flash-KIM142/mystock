package com.example.mystock.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
        private double v;
        @JsonProperty("o")
        private double o;
        @JsonProperty("c")
        private double c;
        @JsonProperty("h")
        private double h;
        @JsonProperty("l")
        private double l;
        @JsonProperty("t")
        private long t;
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
