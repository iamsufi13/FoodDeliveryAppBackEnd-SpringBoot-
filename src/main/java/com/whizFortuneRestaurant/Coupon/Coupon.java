package com.whizFortuneRestaurant.Coupon;

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
    @JoinColumn(name = "product_id")
    private Product product; // Ensure this matches your Product class

    private String couponCode;

    private int discount;

    private String description;

    private LocalDate valid_till_date;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }

    // Removed setter for dt1 if not needed, otherwise keep it
}
