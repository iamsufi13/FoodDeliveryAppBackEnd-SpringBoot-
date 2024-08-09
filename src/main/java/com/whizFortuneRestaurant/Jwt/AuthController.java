package com.whizFortuneRestaurant.Jwt;

import com.whizFortuneRestaurant.Security.JwtHelper;
import com.whizFortuneRestaurant.Users.OtpService;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository; // Assuming you need this for user lookups

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        // Validate OTP
        if (!otpService.validateOtp(request.getMobile(), request.getOtp())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Fetch user details by mobile number
        User user = userRepository.findByMobile(request.getMobile());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailId());
        String token = this.helper.generateToken(userDetails);

        // Remove OTP after successful authentication
        otpService.removeOtp(request.getMobile());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
