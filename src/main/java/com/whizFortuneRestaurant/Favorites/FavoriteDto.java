package com.whizFortuneRestaurant.Favorites;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FavoriteDto {
    private long id;

    private long userId;

    private long productId;

    private String productName;

    private String productDescription;

    public FavoriteDto(long l, long l1,String s, String s1) {
    }
}
