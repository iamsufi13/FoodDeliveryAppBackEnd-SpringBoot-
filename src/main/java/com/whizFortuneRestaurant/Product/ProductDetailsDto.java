package com.whizFortuneRestaurant.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDetailsDto {
    private long id;
    private String name;
    private String description;
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

    private int qty;


}
