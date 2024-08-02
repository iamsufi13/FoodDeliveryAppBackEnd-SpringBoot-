package com.whizFortuneRestaurant.NutritionData;

import com.whizFortuneRestaurant.Product.ProductMapper;

public class NutritionDataMapper {
    public static NutritionDataDto toNutritionDataDto(NutritionData nutritionData) {
        if (nutritionData == null) {
            return null;
        }

        return new NutritionDataDto(
                nutritionData.getId(),
                nutritionData.getCalories(),
                nutritionData.getProteins(),
                nutritionData.getCarbs(),
                nutritionData.getFats(),
                nutritionData.getStatus(),
                nutritionData.getDt1(),
                ProductMapper.toProductDto(nutritionData.getProduct()) // Map the product to ProductDto
        );
    }
}
