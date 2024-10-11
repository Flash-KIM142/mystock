package com.example.mystock.message;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageScheduler {

    private final DiscordClient discordClient;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void sendDailyMessage() {
        DiscordMessageEmbedDto embed = DiscordMessageEmbedDto.builder()
                .title("title")
                .description("# test ## test")
                .build();

        DiscordMessageDto message = DiscordMessageDto.builder()
                .content("content")
                .embeds(List.of(embed))
                .build();

        discordClient.sendMessage(message);
    }
}
