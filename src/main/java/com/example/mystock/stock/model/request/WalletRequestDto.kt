package com.example.mystock.stock.model.request

import java.math.BigDecimal

data class WalletRequestDto(
    val ticker: String,
    val totalUnits: BigDecimal,
    val totalPrice: BigDecimal,
    val upperLimit: BigDecimal,
    val lowerLimit: BigDecimal
) {
    companion object {
        fun of(content: List<String>): WalletRequestDto {
            return WalletRequestDto(
                ticker = content[0],
                totalUnits = BigDecimal(content[1]),
                totalPrice = BigDecimal(content[2]),
                upperLimit = BigDecimal(content[3]),
                lowerLimit = BigDecimal(content[4])
            )
        }
    }
}
