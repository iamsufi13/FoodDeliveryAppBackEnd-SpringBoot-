package com.whizFortuneRestaurant.OrderProduct;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whizFortuneRestaurant.Orders.Orders;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductDetailsDto;
import com.whizFortuneRestaurant.Product.ProductOrderDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}

