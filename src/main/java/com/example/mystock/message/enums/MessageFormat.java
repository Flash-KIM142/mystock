package com.example.mystock.message.enums;

import java.util.ArrayList;
import java.util.List;

public enum MessageFormat {

    ADD_OWN_STOCK(
                    "+---------------------------------------------------------------+\n" +
                    "| ADD OWN STOCK                                                                                    |\n" +
                    "| {ticker},{total_units},{total_price},{upper_limit},{lower_limit}|\n" +
                    "+---------------------------------------------------------------+",

            "+----------------------------+\n" +
                    "| **Example**                                   |\n" +
                    "+----------------------------+\n" +
                    "| ADD OWN STOCK                  |\n" +
                    "| AAPL,3.0,1800.00,15.0,-5.0 |\n" +
                    "+----------------------------+"
    ),

    ADD_FAV_STOCK(
            "+---------------------------------------------------------------+\n" +
                    "| ADD FAV STOCK                                                                                      |\n" +
                    "| {ticker}                                                                                                        |\n" +
                    "+---------------------------------------------------------------+",

            "+----------------------------+\n" +
                    "| **Example**                                   |\n" +
                    "+----------------------------+\n" +
                    "| ADD FAV STOCK                     |\n" +
                    "| GOOGL                                      |\n" +
                    "+----------------------------+"
    ),

    PREV_CLOSE(
                    "+---------------------------------------------------------------+\n" +
                    "| PREV CLOSE                                                                                            |\n" +
                    "| {ticker}                                                                                                       |\n" +
                    "+---------------------------------------------------------------+",

            "+----------------------------+\n" +
                    "| **Example**                                   |\n" +
                    "+----------------------------+\n" +
                    "| PREV CLOSE                            |\n" +
                    "| VOO                                            |\n" +
                    "+----------------------------+"
    );

    private final String format;
    private final String example;

    MessageFormat(String format, String example) {
        this.format = format;
        this.example = example;
    }

    public String getFormat() {
        return format;
    }

    public String getExample() {
        return example;
    }

    public static List<String> getHelpMessage() {
        List<String> helpMessages = new ArrayList<>();
        helpMessages.add("+--------------------------------+\n" +
                "|           **Available Commands**        |\n" +
                "+--------------------------------+\n\n");
        for (MessageFormat messageType : MessageFormat.values()) {
            StringBuilder helpMessage = new StringBuilder();
            helpMessage.append("\n\n\n\n")
                    .append("**Format of <")
                    .append(messageType.name())
                    .append(">**\n")
                    .append(messageType.getFormat())
                    .append("\n\n")
                    .append(messageType.getExample())
                    .append("\n\n");
            helpMessages.add(helpMessage.toString());
        }
        return helpMessages;
    }
}
