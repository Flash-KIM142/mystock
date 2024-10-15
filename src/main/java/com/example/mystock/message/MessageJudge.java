package com.example.mystock.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageJudge {

    @Getter
    @AllArgsConstructor
    public static class MessageTypeAndContent {
        private MessageType messageType;
        private String ticker;
        private List<String> content;
    }

    public enum MessageType {
        ADD_OWN_STOCK,
        ADD_FAV_STOCK,
        PREV_CLOSE,
        UNKNOWN
    }

    public MessageTypeAndContent judge(String message) {
        if(message == null || message.isEmpty()) {
            return new MessageTypeAndContent(MessageType.UNKNOWN, "", List.of());
        }

        String[] splits = message.split("\n", 2);
        if(splits.length < 2 || splits[1].isEmpty()) {
            return new MessageTypeAndContent(MessageType.UNKNOWN, "", List.of());
        }

        List<String> content = Arrays.asList(splits[1].split(","));

        if(splits[0].startsWith("ADD OWN STOCK")) {
            return new MessageTypeAndContent(MessageType.ADD_OWN_STOCK, content.get(0), content);
        }
        else if(splits[0].startsWith("ADD FAV STOCK")) {
            return new MessageTypeAndContent(MessageType.ADD_FAV_STOCK, content.get(0), content);
        }
        else if(splits[0].startsWith("PREV CLOSE")) {
            return new MessageTypeAndContent(MessageType.PREV_CLOSE, content.get(0), content);
        }
        else {
            return new MessageTypeAndContent(MessageType.UNKNOWN, "", content);
        }
    }
}
