package com.whizFortuneRestaurant.OrderStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.Orders.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(mappedBy = "orderStatus")
    private Orders order;

    private String ordertype;

    private String orderstatus;


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
