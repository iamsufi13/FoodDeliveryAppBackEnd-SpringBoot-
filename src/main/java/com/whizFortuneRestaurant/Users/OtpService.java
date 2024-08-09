package com.whizFortuneRestaurant.Users;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private final Map<Long, String> otpStore = new HashMap<>();

    public void storeOtp(long mobile, String otp) {
        otpStore.put(mobile, otp);
    }

    public boolean validateOtp(long mobile, String otp) {
        String storedOtp = otpStore.get(mobile);
        return otp.equals(storedOtp);
    }
    public void removeOtp(long mobile) {
        otpStore.remove(mobile);
    }
}
