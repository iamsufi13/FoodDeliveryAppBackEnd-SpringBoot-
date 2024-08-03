package com.whizFortuneRestaurant.ComboProducts;

import com.whizFortuneRestaurant.Product.FoodType;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class ComboProductsDto {
    private long id;
    private String description;
    private int price;
    private int mrp;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private int oniongarlic;
    private int freedelivery;
    private int spicy;

    private List<ComboProductDto> products;
}
