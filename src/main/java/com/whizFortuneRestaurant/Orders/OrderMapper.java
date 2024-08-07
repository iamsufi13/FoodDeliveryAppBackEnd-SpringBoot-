package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.OrderProduct.OrderProduct;
import com.whizFortuneRestaurant.OrderProduct.OrderProductDto;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductOrderDto;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrderDto(Orders orders) {
        if (orders == null) {
            return null;
        }

        return new OrderDto(
                orders.getId(),
                orders.getTxnid(),
                orders.getOrderProducts() != null ? orders.getOrderProducts().stream()
                        .map(op -> {
                            OrderProductDto dto = new OrderProductDto();
                            dto.setId(op.getId());

                            Product product = op.getProduct();

                            if (product != null) {
                                ProductOrderDto productOrderDto = new ProductOrderDto();
                                productOrderDto.setId(product.getId());
                                productOrderDto.setName(product.getName());
                                productOrderDto.setQty(product.getQty()); // Assuming `getQty()` is available

                                dto.setProductDto(productOrderDto);
                            }

                            return dto;
                        }).collect(Collectors.toList()) : null,
                orders.getAvailableSizes() != null ? orders.getAvailableSizes().stream()
                        .map(AvailableSizes::getId).collect(Collectors.toList()) : null,
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
