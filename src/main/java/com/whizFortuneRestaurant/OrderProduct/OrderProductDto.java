package com.whizFortuneRestaurant.OrderProduct;


import com.whizFortuneRestaurant.Product.ProductOrderDto;
import lombok.Data;

@Data
public class OrderProductDto {
    private long id;

    private ProductOrderDto productDto;

}
