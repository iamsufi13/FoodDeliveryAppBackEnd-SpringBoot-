package com.whizFortuneRestaurant.Product;

import com.fasterxml.jackson.annotation.*;
import com.whizFortuneRestaurant.AvailableCustomization.AvailableCustomization;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.Cart.Cart;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProductDetails.ComboProductDetails;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.Coupon.Coupon;
import com.whizFortuneRestaurant.DealOfTheDay.DealOfTheDay;
import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.NutritionData.NutritionData;
import com.whizFortuneRestaurant.Orders.Orders;
import com.whizFortuneRestaurant.WishList.WishList;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"favorites"})  // To prevent infinite recursion in JSON serialization
public class Product implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private int mrp;
    private int customizationAvailable;
    private String image;
    private long serveFor;
    private String sellerTags;
    private int customizationStatus;
    private int nutritionStatus;
    private int spicyStatus;
    private int freeDeliveryStatus;
    private int sellPrice;
    private int onionGarlicStatus;

    @ManyToOne
    @JoinColumn(name = "catlog_id")
    private Catlogs catlogs;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSizes> availableSizes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableCustomization> availableCustomizations;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionData nutritionData;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private DealOfTheDay dealOfTheDay;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

//    @ManyToOne
//    @JoinColumn(name = "comboproduct_id")
////    @JsonIgnore
//    private ComboProducts comboproducts;

    @OneToMany(mappedBy = "product")
    private Set<WishList> wishListSet;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ComboProductDetails> comboProductDetails;
}
