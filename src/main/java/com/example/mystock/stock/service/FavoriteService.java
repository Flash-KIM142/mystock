package com.example.mystock.stock.service;

import com.example.mystock.message.MessageBuilder;
import com.example.mystock.stock.model.Favorite;
import com.example.mystock.stock.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MessageBuilder messageBuilder;

    public String save(String ticker, String prevClose) {
        Favorite favorite = Favorite.of(ticker);
        favoriteRepository.save(favorite);
        return messageBuilder.addFavStock(ticker, prevClose);
    }

    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }
}
