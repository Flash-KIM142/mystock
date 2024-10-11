package com.example.mystock.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discordClient",
        url = "${discord.webhook.url}"
)
public interface DiscordClient {

    @PostMapping()
    void sendMessage(@RequestBody DiscordMessageDto message);
}
