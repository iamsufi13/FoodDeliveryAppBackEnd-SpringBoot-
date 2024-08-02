package com.whizFortuneRestaurant.DealOfTheDay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class DealOfTheDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int offer_price;

    private LocalDate end_date;

    private int status;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void perpersist(){
        this.dt1=LocalDateTime.now();

    }

    public void dt1(LocalDateTime dt1){
        this.dt1=dt1;
    }
}
