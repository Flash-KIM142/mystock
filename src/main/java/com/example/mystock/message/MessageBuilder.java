package com.example.mystock.message;

import com.example.mystock.stock.model.Wallet;
import org.springframework.stereotype.Component;

@Component
public class MessageBuilder {

    public String addOwnStock(Wallet wallet) {
        return String.format("celebrate on your newly added stock %s!\n" +
                        "Total Units: %s\n" +
                        "Total Price: %s\n" +
                        "Average Price: %s\n" +
                        "Profit Rate: %s%%\n" +
                        "You will sell this stock when it hits %s%%\n" +
                        "You will sell this stock when it goes down to %s%%",
                wallet.getTicker(), wallet.getTotalUnits(), wallet.getTotalPrice(),
                wallet.getAveragePrice(), wallet.getProfitRate(), wallet.getUpperLimit(),
                wallet.getLowerLimit());
    }

    public String addFavStock(String ticker, String prevClose) {
        return String.format("Ticker %s is now safely added to your favorite list!\n" +
                "Here's the previous close information of %s\n\n" +
                "%s",
                ticker, ticker, prevClose);
    }
}
