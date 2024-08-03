package com.whizFortuneRestaurant.Address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.Users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference("user-addresses")
    private User user;

    private String name;

    private long mobile;

    private int pincode;

    private String state;

    private String address;

    private String locality;

    private String city;

    private int weekenddelivery;

    private String addresstype;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void  prePersist(){
        this.dt1=LocalDateTime.now();
    }
    public void dt1(LocalDateTime dt1){
        this.dt1=dt1;
    }
}
