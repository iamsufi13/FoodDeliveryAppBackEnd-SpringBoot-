package com.whizFortuneRestaurant.Product;

import com.whizFortuneRestaurant.NutritionData.NutritionData;
import com.whizFortuneRestaurant.ReviewsRating.ReviewRatingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDetailsDto {
    private long id;
    private String name;
    private String description;
    private FoodType foodType;
    private int mrp;
    private String image;
    private long serveFor;
    private String sellerTags;
    private int spicyStatus;
    private int freeDeliveryStatus;
    private int sellPrice;
    private int onionGarlicStatus;
    private NutritionData nutritionData;
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

