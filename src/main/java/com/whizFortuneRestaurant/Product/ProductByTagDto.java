package com.whizFortuneRestaurant.Product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.ReviewsRating.ReviewRatingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductByTagDto {
    private long id;
    private String name;
    private String description;
    private FoodType foodType;
    private String image;
    private int mrp;
    @JsonBackReference
    private List<ReviewRatingDto> reviews;

    private double averageRating;

    public void calculateAverageRating(){
        if (reviews!=null && !reviews.isEmpty()){
            this.averageRating=reviews.stream().mapToDouble(ReviewRatingDto::getRating).average().orElse(0.0);
        }
        else {
            this.averageRating = 0.0;
        }
    }
}
