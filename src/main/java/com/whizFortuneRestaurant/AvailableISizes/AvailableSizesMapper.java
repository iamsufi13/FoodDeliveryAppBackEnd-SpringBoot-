package com.whizFortuneRestaurant.AvailableISizes;

import com.whizFortuneRestaurant.Product.ProductMapper;

public class AvailableSizesMapper {
    public static AvailableSizesDto toAvailableSizesDto(AvailableSizes availableSizes) {
        if (availableSizes == null) {
            return null;
        }

        return new AvailableSizesDto(
                availableSizes.getId(),
                availableSizes.getSize_name(),
                availableSizes.getPrice(),
                availableSizes.getStatus(),
                availableSizes.getDt1(),
                ProductMapper.toProductDto(availableSizes.getProduct()) // Map the product to ProductDto
        );
    }
}
