package com.whizFortuneRestaurant.NutritionData;

import com.whizFortuneRestaurant.Product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NutritionDataDto {
    private long id;
    private int calories;
    private int proteins;
    private int carbs;
    private int fats;
    private int status;
    private LocalDateTime dt1;
    private ProductDto product;
}
