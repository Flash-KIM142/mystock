package com.example.mystock.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "alphaVintageClient",
        url = "${alphavantage.api.url}"
)
public interface AlphaVantageClient {

    @GetMapping("/query")
    StockResponse getStock(
            @RequestParam("function") String function,
            @RequestParam("symbol") String symbol,
            @RequestParam("interval") String interval,
            @RequestParam("apikey") String apikey
    );
}
