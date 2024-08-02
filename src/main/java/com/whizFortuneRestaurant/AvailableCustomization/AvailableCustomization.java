package com.whizFortuneRestaurant.AvailableCustomization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.CustomizationProduct.CustomizationProduct;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AvailableCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-customizations")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customization_id")
    private CustomizationProduct customizationproduct;

    private int status;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist(){
        this.dt1=LocalDateTime.now();
    }

    public void dt1(LocalDateTime dt1){
        this.dt1=dt1;
    }
}
