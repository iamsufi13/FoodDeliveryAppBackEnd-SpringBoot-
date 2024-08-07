package com.whizFortuneRestaurant.Product;

import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.ReviewsRating.ReviewRating;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductListingDto {
    private long id;
    private String name;
    private String description;
    private FoodType foodType;
    private String sellerTags;
    private int mrp;
    private String image;
    private int spicy;
    private long servefor;
    private List<Favorite> isFavorite;


}

