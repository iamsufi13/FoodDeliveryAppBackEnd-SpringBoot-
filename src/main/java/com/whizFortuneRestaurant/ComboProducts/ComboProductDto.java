package com.whizFortuneRestaurant.ComboProducts;

import lombok.Data;

@Data
public class ComboProductDto {
    private long productId;
    private int quantity; // Quantity of this product in the combo
}
