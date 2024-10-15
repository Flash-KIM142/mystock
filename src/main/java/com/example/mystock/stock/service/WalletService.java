package com.example.mystock.stock.service;

import com.example.mystock.message.MessageBuilder;
import com.example.mystock.stock.model.Wallet;
import com.example.mystock.stock.model.request.WalletRequestDto;
import com.example.mystock.stock.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final MessageBuilder messageBuilder;

    public String save(WalletRequestDto req, BigDecimal prevClose) {
        Wallet wallet = Wallet.of(req, prevClose);
        Wallet saved = walletRepository.save(wallet);
        return messageBuilder.addOwnStock(saved);
    }
}
