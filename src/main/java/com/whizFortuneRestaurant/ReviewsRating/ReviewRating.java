package com.whizFortuneRestaurant.ReviewsRating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.whizFortuneRestaurant.Users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private long rating;

    private String reviewhead;

    private String
    reviewmessage;

    private LocalDate date;

    @JsonIgnore
    private LocalDateTime dt1;
    @PrePersist
    public void prePersist(){
        this.dt1=LocalDateTime.now();
    }
    public void dt1(LocalDateTime dt1){
        this.dt1=dt1;
    }

    private int status;

}
