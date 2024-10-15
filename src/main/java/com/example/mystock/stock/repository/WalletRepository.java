package com.example.mystock.stock.repository;

import com.example.mystock.stock.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
