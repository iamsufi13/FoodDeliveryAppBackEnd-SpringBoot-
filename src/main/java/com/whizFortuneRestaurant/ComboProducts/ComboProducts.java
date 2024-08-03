package com.whizFortuneRestaurant.ComboProducts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.ComboProductDetails.ComboProductDetails;
import com.whizFortuneRestaurant.Product.FoodType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ComboProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private int price;

    private int mrp;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private int oniongarlic;

    private int spicy;

    private int freedeliverystatus;

    @OneToMany(mappedBy = "comboProducts", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ComboProductDetails> productDetails;
}
