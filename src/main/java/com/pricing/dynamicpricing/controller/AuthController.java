package com.pricing.dynamicpricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pricing.dynamicpricing.service.EmailService;
import com.pricing.dynamicpricing.service.OtpService;

import java.util.Map;
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    OtpService otpService;

    @Autowired
    EmailService emailService;

    private final String USER = "vaziya karim";
    private final String PASS = "768852";
    private final String EMAIL = "vaziyakarim816@gmail.com";
@PostMapping("/login")
public String login(@RequestBody Map<String,String> body){

    String username = body.get("username");
    String password = body.get("password");

    if(USER.trim().equalsIgnoreCase(username.trim()) &&
       PASS.trim().equals(password.trim())){

        String otp = otpService.generateOtp();

        // Print OTP instead of sending email
        System.out.println("Generated OTP: " + otp);

        return "OTP_SENT";
    }

    return "Authentication failed";
}
    @PostMapping("/verify")
    public String verify(@RequestBody Map<String,String> body){

        String otp = body.get("otp");

        if(otpService.verifyOtp(otp)){
            return "SUCCESS";
        }

        return "Invalid OTP";
    }
    
}