package com.example.mystock.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DiscordMessageEmbedDto {

    private String title;
    private String description;
}
