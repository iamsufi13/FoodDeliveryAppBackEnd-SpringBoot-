package com.whizFortuneRestaurant.Orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.OrderStatus.OrderStatus;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long txnid;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference(value = "productsorders")
    List<Product> products;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSizes> availableSizes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int price;
    private int mrp;
    private int qty;
    private int status;
    private int discountprice;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }
}
