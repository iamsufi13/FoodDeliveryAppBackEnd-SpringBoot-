package com.whizFortuneRestaurant.Cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-carts")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("productcarts")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @JsonBackReference("size-carts")
    private AvailableSizes availableSizes;

    private int qty;

    private int price;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist(){
        this.dt1 = LocalDateTime.now();
    }

    public void setDt1(LocalDateTime dt1){
        this.dt1 = dt1;
    }
}
