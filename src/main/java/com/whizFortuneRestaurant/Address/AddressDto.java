package com.whizFortuneRestaurant.Address;

import lombok.Data;

@Data
public class AddressDto {
    private long id;
    private String name;

    private long mobile;

    private int pincode;

    private String state;

    private String address;

    private String locality;

    private String city;

    private int weekenddelivery;

    private String addresstype;

    private long userId;

}
