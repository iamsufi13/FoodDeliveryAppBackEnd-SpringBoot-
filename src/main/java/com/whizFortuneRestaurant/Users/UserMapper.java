package com.whizFortuneRestaurant.Users;

import java.time.LocalDate;

public class UserMapper {
    public static UserDto toUserDto(User user){
        if (user==null){
            return null;
        }
        return new UserDto(
                user.getUserid(),
                user.getUsername(),
                user.getMobile(),
                user.getEmailId(),
                user.getDob(),
                user.getGender(),
                user.getAnniversarydate(),
                user.getDevicetype(),
                user.getStatus(),
                user.getFcmtoken()
        );
    }
}
