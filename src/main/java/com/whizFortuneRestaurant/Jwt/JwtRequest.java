package com.whizFortuneRestaurant.Jwt;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private long mobile;
    private String otp;

}
