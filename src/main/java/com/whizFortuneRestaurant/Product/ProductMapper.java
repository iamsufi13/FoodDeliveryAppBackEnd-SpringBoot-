package com.whizFortuneRestaurant.Product;

import com.whizFortuneRestaurant.WishList.WishList;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getFoodType(),
                product.getMrp(),
                product.getCustomizationAvailable(),
                product.getImage(),
                product.getServeFor(),
                product.getSellerTags(),
                product.getCustomizationStatus(),
                product.getNutritionStatus(),
                product.getSpicyStatus(),
                product.getFreeDeliveryStatus(),
                product.getSellPrice(),
                product.getOnionGarlicStatus(),
                product.getCatlogs()
        );
    }
}
