package com.example.mystock.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

import java.util.List;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DiscordMessageDto {

    private String content;
    private List<DiscordMessageEmbedDto> embeds;
}
