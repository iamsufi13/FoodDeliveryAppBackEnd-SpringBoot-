package com.whizFortuneRestaurant.Catlog1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import com.whizFortuneRestaurant.Product.Product;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Catlogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(nullable = true)
    private String icon;

    @Column(nullable = true)
    private String bannerImage;

    private int status;

    @OneToMany(mappedBy = "catlogs", cascade = CascadeType.ALL)
    @JsonIgnore

    private List<Product> products;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }
}
