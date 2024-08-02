package com.whizFortuneRestaurant.Favorites;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;

public class FavoriteMapper {
    public static FavoriteDto toDto(Favorite favorite){
        if (favorite == null) {
            return null;
        }
        return new FavoriteDto(
                favorite.getId(),
                favorite.getUser() != null ? favorite.getUser().getUserid() : 0,
                favorite.getProduct() != null ? favorite.getProduct().getId() : 0,
                favorite.getProduct()!=null ? favorite.getProduct().getName() :"",
                favorite.getProduct()!= null ? favorite.getProduct().getDescription():""
        );
    }

    public static Favorite fromDto(FavoriteDto favoriteDto, User user, Product product){
        Favorite favorite = new Favorite();
        favorite.setId(favoriteDto.getId());
        favorite.setUser(user);
        favorite.setProduct(product);
        return favorite;
    }
}
