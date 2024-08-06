package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.OrderProduct.OrderProduct;
import com.whizFortuneRestaurant.Product.Product;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrderDto(Orders orders){
        if (orders == null){
            return null;
        }

       return new OrderDto(
                orders.getId(),
                orders.getTxnid(),
                orders.getProducts() != null ? orders.getProducts().stream().map(Product::getId).collect(Collectors.toList()) : null,
                orders.getOrderProducts()!= null ? orders.getOrderProducts().stream().collect(Collectors.toList()) : null,
                orders.getAvailableSizes() != null ? orders.getAvailableSizes().stream().map(AvailableSizes::getId).collect(Collectors.toList()) : null,
                orders.getOrderStatus() != null ? orders.getOrderStatus().getId() : 0,
                orders.getUser() != null ? orders.getUser().getUserid() : 0,
                orders.getPrice(),
                orders.getMrp(),
                orders.getQty(),
                orders.getStatus(),
                orders.getDiscountprice(),
                orders.getDt1()
        );
    }

}
