package com.whizFortuneRestaurant.Product;

import lombok.Data;

@Data
public class ProductOrderDto {
    private long id;

    private String name;

    private int qty;
}
