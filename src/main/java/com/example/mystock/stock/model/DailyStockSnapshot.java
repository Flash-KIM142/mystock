package com.example.mystock.stock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_stock_snapshot")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyStockSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "dataDate", nullable = false)
    private LocalDateTime dataDate;

    @Column(name = "closePrice", precision = 19, scale = 4, nullable = false)
    private BigDecimal closePrice;

    @Column(name = "highPrice", precision = 19, scale = 4, nullable = false)
    private BigDecimal highPrice;

    @Column(name = "lowPrice", precision = 19, scale = 4, nullable = false)
    private BigDecimal lowPrice;
}
