package com.example.mystock.stock.model;

import com.example.mystock.stock.model.request.WalletRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "wallets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ticker")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ticker")
    @NotNull
    private String ticker;

    @Column(name = "total_units")
    @NotNull
    private BigDecimal totalUnits;

    @Column(name = "total_price")
    @NotNull
    private BigDecimal totalPrice;

    @Column(name = "average_price")
    private BigDecimal averagePrice;

    @Column(name = "profit_rate")
    private BigDecimal profitRate;

    @Column(name = "upper_limit")
    private BigDecimal upperLimit;

    @Column(name = "lower_limit")
    private BigDecimal lowerLimit;

    public static Wallet of(WalletRequestDto req, BigDecimal prevClose) {
        BigDecimal averagePrice = req.getTotalPrice().divide(req.getTotalUnits(), 3, RoundingMode.HALF_UP);
        BigDecimal profitRate = prevClose.subtract(averagePrice)
                .divide(req.getTotalPrice(), 3, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        Wallet wallet = Wallet.builder()
                .ticker(req.getTicker())
                .totalUnits(req.getTotalUnits())
                .totalPrice(req.getTotalPrice())
                .averagePrice(averagePrice)
                .profitRate(profitRate)
                .upperLimit(req.getUpperLimit())
                .lowerLimit(req.getLowerLimit())
                .build();

        return wallet;
    }
}
