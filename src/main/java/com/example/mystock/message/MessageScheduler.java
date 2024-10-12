package com.example.mystock.message;

import com.example.mystock.stock.AlphaVantageClient;
import com.example.mystock.stock.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageScheduler {

    private final DiscordClient discordClient;
    private final AlphaVantageClient alphaVantageClient;

    @Scheduled(cron = "0 30 18 * * MON-SUN", zone = "Asia/Seoul")
    public void sendDailyMessage() {
        List<String> stockSymbols = List.of("AAPL", "GOOGL");
        String interval = "5min";
        for(String symbol : stockSymbols) {
            StockResponse stockResponse = alphaVantageClient.getStock("TIME_SERIES_INTRADAY", symbol, interval,
                    "${alphavantage.api.key}");

            DiscordMessageEmbedDto embed = DiscordMessageEmbedDto.builder()
                    .title(symbol + " Stock Summary")
                    .description(stockResponse.buildLatestDailyStockDescription())
                    .build();

            DiscordMessageDto message = DiscordMessageDto.builder()
                    .content("Daily stock summary for " + symbol)
                    .embeds(List.of(embed))
                    .build();

            discordClient.sendMessage(message);
        }
    }
}
