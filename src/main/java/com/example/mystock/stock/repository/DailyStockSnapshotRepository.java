package com.example.mystock.stock.repository;

import com.example.mystock.stock.model.DailyStockSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStockSnapshotRepository extends JpaRepository<DailyStockSnapshot, Long> {
}
