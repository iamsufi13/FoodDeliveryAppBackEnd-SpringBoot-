package com.whizFortuneRestaurant.ComboProducts;

import com.whizFortuneRestaurant.ComboProductDetails.ComboProductDetails;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductOrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class ComboProductsMapper {

    public static ComboProductsDto toComboProductsDto(ComboProducts comboProducts) {
        if (comboProducts == null) {
            return null;
        }

        ComboProductsDto dto = new ComboProductsDto();
        dto.setId(comboProducts.getId());
        dto.setDescription(comboProducts.getDescription());
        dto.setPrice(comboProducts.getPrice());
        dto.setMrp(comboProducts.getMrp());
        dto.setFoodType(comboProducts.getFoodType());
        dto.setOniongarlic(comboProducts.getOniongarlic());
        dto.setSpicy(comboProducts.getSpicy());
        dto.setFreedelivery(comboProducts.getFreedeliverystatus());

        List<ComboProductDto> productDtos = comboProducts.getProductDetails().stream()
                .map(detail -> {
                    ComboProductDto productDto = new ComboProductDto();
                    productDto.setProductId(detail.getProduct().getId());
                    productDto.setQuantity(detail.getQuantity());
                    return productDto;
                })
                .collect(Collectors.toList());

        dto.setProducts(productDtos);

        return dto;
    }
}
