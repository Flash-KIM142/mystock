package com.example.mystock.stock.model.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class WalletRequestDto {

    private String ticker;
    private BigDecimal totalUnits;
    private BigDecimal totalPrice;
    private BigDecimal upperLimit;
    private BigDecimal lowerLimit;

    public static WalletRequestDto of(List<String> content) {
        return WalletRequestDto.builder()
                .ticker(content.get(0))
                .totalUnits(new BigDecimal(content.get(1)))
                .totalPrice(new BigDecimal(content.get(2)))
                .upperLimit(new BigDecimal(content.get(3)))
                .lowerLimit(new BigDecimal(content.get(4)))
                .build();
    }
}
