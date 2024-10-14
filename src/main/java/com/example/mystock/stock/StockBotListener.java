package com.example.mystock.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockBotListener extends ListenerAdapter {

    private final PolygonClient polygonClient;

    @Value("${polygon.api.key}")
    private String polygonApiKey;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String symbol = event.getMessage().getContentRaw();
        PolygonStockResponse polygonStockResponse = polygonClient.getPrevStock(symbol, polygonApiKey);

        String prevStockDescription = polygonStockResponse.getPrevStockDescription();
        event.getChannel().sendMessage(prevStockDescription).queue();
    }
}
