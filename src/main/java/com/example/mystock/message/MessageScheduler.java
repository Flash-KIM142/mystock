package com.example.mystock.message;

import com.example.mystock.stock.AlphaVantageClient;
import com.example.mystock.stock.StockResponse;
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

    @Value("${discord.channel.id}")
    private String channelId;

    @Scheduled(cron = "0 0 6 * * TUE-SAT", zone = "Asia/Seoul")
    public void sendDailyMessage() {
        List<String> stockSymbols = List.of("AAPL", "GOOGL");
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
