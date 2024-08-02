package com.whizFortuneRestaurant.AvailableISizes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizFortuneRestaurant.Cart.Cart;
import com.whizFortuneRestaurant.Orders.Orders;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class AvailableSizes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-sizes")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders orders;

    @OneToMany(mappedBy = "availableSizes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("size-carts")
    private List<Cart> carts;

    private String size_name;

    private int price;

    private int status;

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
