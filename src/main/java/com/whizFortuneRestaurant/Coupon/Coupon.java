package com.whizFortuneRestaurant.Coupon;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String coupon_type;

    @ManyToOne
    @JsonBackReference(value = "product-coupons")
    @JoinColumn(name = "product_id", columnDefinition = "bigint default 0")
    private Product product;

    private String couponCode;

    private int discount;

    private String description;

    private LocalDate valid_till_date;

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
