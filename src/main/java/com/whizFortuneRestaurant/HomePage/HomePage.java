package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class HomePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Catlogs> catlogs;

    @OneToMany
    private List<Product> products;
}
