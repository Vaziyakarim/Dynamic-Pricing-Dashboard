package com.pricing.dynamicpricing.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class OtpService {

    private String otp;
    private long otpTime;

    public String generateOtp(){

        Random r = new Random();
        int code = 1000 + r.nextInt(9000);

        otp = String.valueOf(code);
        otpTime = System.currentTimeMillis();

        return otp;
    }

    public boolean verifyOtp(String input){

        long now = System.currentTimeMillis();

        if(now - otpTime > 60000){
            return false;
        }

        return input.equals(otp);
    }
}