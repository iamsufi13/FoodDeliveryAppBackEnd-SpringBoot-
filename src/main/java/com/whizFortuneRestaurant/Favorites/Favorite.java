package com.whizFortuneRestaurant.Favorites;

import com.fasterxml.jackson.annotation.*;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Favorite implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }
}
