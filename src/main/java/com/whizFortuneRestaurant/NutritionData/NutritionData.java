package com.whizFortuneRestaurant.NutritionData;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class NutritionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne
//    @JoinColumn(name = "product_id")
//    @JsonBackReference(value = "product-nutrition")
//    private Product product;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int calories;

    private int proteins;

    private int carbs;

    private int fats;

    private int status;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void perPersist(){
        this.dt1=LocalDateTime.now();

    }
    private void dt1(LocalDateTime ft1){
        this.dt1=dt1;
    }


}
