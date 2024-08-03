package com.whizFortuneRestaurant.Users;

import com.fasterxml.jackson.annotation.*;
import com.whizFortuneRestaurant.Address.Address;
import com.whizFortuneRestaurant.Cart.Cart;
import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.WishList.WishList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userid")
public class User implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private long mobile;

    @Column(unique = true)
    private String emailId;

    private LocalDate dob;

    private String gender;

    private LocalDate anniversarydate;

    private String devicetype;

    private int status;

    private String fcmtoken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-carts")
    List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-favorites")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private Set<WishList> wishListSet;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }
}
