package com.whizFortuneRestaurant.Favorites;

import com.whizFortuneRestaurant.Cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;

    public List<FavoriteDto> getAllFavorite() {
        List<Favorite> favorites= favoriteRepository.findAll();
       return favorites.stream().map(FavoriteMapper::toDto).collect(Collectors.toList());
    }

    public Favorite getFavoriteById(long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    public void addToFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }


    public Favorite updateFavorite(long id, Favorite favorite) {
        Favorite favorite1 = getFavoriteById(id);
        favorite1.setUser(favorite.getUser());
        favorite1.setProduct(favorite.getProduct());
        return favoriteRepository.save(favorite1);
    }
    public void deleteFavoriteById(long id){
        favoriteRepository.deleteById(id);
    }
}
