package com.whizFortuneRestaurant.ComboProducts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.Product.FoodType;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ComboProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "comboproducts",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "combo-products")
    private List<Product> products;

    private String description;

    private int price;

    private int mrp;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private int oniongarlic;

    private int spicy;

    private int freedeliverystatus;
}
