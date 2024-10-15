package com.example.mystock.stock;

import com.example.mystock.message.MessageJudge;
import com.example.mystock.stock.model.request.WalletRequestDto;
import com.example.mystock.stock.service.FavoriteService;
import com.example.mystock.stock.service.WalletService;
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
    private final WalletService walletService;
    private final FavoriteService favoriteService;
    private final MessageJudge messageJudge;

    @Value("${polygon.api.key}")
    private String polygonApiKey;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String message = event.getMessage().getContentRaw();
        log.info("Received message {}", message);

        MessageJudge.MessageTypeAndContent messageTypeAndContent = messageJudge.judge(message);
        if(messageTypeAndContent.getMessageType()== MessageJudge.MessageType.UNKNOWN) {
            event.getChannel().sendMessage("Invalid Message. Please send the message in given format.").queue();
            return;
        }

        String ticker = messageTypeAndContent.getTicker();
        log.info("Message Type {}", messageTypeAndContent.getMessageType());
        log.info("ticker {}", ticker);
        PolygonStockResponse polygonStockResponse = polygonClient.getPrevStock(ticker, polygonApiKey);
        String prevStockDescription = polygonStockResponse.getPrevStockDescription();
        String responseMessage;
        switch (messageTypeAndContent.getMessageType()) {
            case ADD_OWN_STOCK -> {
                responseMessage = walletService.save(WalletRequestDto.of(messageTypeAndContent.getContent()),
                        polygonStockResponse.getPrevClose());
                event.getChannel().sendMessage(responseMessage).queue();
            }
            case ADD_FAV_STOCK -> {
                responseMessage = favoriteService.save(messageTypeAndContent.getContent().get(0),
                        prevStockDescription);
                event.getChannel().sendMessage(responseMessage).queue();
            }
            case PREV_CLOSE -> {
                responseMessage = polygonStockResponse.getPrevStockDescription();
                event.getChannel().sendMessage(responseMessage).queue();
            }
        }
    }
}
