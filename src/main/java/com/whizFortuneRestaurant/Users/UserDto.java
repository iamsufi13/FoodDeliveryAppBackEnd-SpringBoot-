package com.whizFortuneRestaurant.Users;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private long userid;
    private String username;
    private long mobile;

    private String emailId;

    private LocalDate dob;

    private String gender;

    private LocalDate anniversarydate;

    private String devicetype;

    private int status;

    private String fcmtoken;

}
