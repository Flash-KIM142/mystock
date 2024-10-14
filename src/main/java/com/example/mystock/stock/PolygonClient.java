package com.example.mystock.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "polygonClient",
        url = "${polygon.api.url}"
)
public interface PolygonClient {

    @GetMapping("/aggs/ticker/{ticker}/prev?adjusted=true")
    PolygonStockResponse getPrevStock(
        @RequestParam("ticker") String ticker,
        @RequestParam("apiKey") String apiKey
    );
}
