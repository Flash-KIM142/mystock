package com.example.mystock.message;

import com.example.mystock.stock.AlphaVantageClient;
import com.example.mystock.stock.StockResponse;
import com.example.mystock.stock.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageScheduler {

    private final AlphaVantageClient alphaVantageClient;
    private final JDA jda;

    private final FavoriteService favoriteService;

    @Value("${discord.channel.id}")
    private String channelId;

    /**
     * 1. Send Favorite Stock Data
     * 2. Send changes from my own stock data (e.g. profit rate, close)
     * */
    @Scheduled(cron = "0 52 22 * * TUE-SAT", zone = "Asia/Seoul")
    public void sendDailyMessage() {
        List<String> stockSymbols = favoriteService.getAll()
                .stream()
                .map(favorite -> favorite.getTicker())
                .toList();
        String interval = "5min";

        TextChannel textChannel = jda.getTextChannelById(channelId);

        if(textChannel != null) {
            for(String symbol : stockSymbols) {
                StockResponse stockResponse = alphaVantageClient.getStock("TIME_SERIES_INTRADAY", symbol, interval,
                        "${alphavantage.api.key}");

                String stockMessage = "Daily stock summary for " + symbol + ":\n" + stockResponse.buildLatestDailyStockDescription();
                textChannel.sendMessage(stockMessage).queue();
            }
        }
        else {
            System.out.println("TextChannel not found for ID: " + channelId);
        }
    }
}
