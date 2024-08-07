package com.whizFortuneRestaurant.ReviewsRating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRatingDto {
    private long id;
    private long userId;
    private long productId;
    private long rating;
    private String reviewmessage;
    private LocalDate date;

}

