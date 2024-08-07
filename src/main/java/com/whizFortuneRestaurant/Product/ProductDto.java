package com.whizFortuneRestaurant.Product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whizFortuneRestaurant.ReviewsRating.ReviewRatingDto;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.Product.FoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private FoodType foodType;
    private int mrp;
    private int customizationAvailable;
    private String image;
    private long serveFor;
    private String sellerTags;
    private int customizationStatus;
    private int nutritionStatus;
    private int spicyStatus;
    private int freeDeliveryStatus;
    private int sellPrice;
    private int onionGarlicStatus;
    private Catlogs catlogs;
    @JsonBackReference
    private List<ReviewRatingDto> reviews;
    private double averageRating;

    public void calculateAverageRating() {
        if (reviews != null && !reviews.isEmpty()) {
            this.averageRating = reviews.stream()
                    .mapToDouble(ReviewRatingDto::getRating)
                    .average()
                    .orElse(0.0);
        } else {
            this.averageRating = 0.0;
        }
    }
}
