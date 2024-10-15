package com.example.mystock.stock.repository;

import com.example.mystock.stock.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
