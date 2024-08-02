package com.whizFortuneRestaurant.CustomizationProduct;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.AvailableCustomization.AvailableCustomization;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CustomizationProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String quantity;

    private int price;

    private int status;

    @OneToMany(mappedBy = "customizationproduct",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AvailableCustomization> availableCustomizations;

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
