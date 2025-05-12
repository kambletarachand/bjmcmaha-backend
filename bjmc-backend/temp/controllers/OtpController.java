package com.bjmc.controllers;



import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.bjmc.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class OtpController {

    private final Map<String, String> otpStorage = new HashMap<>();
    @Autowired
    private SmsService smsService;

//    @PostMapping("/send-otp")
//    public Map<String, String> sendOtp(@RequestBody Map<String, String> request) {
//        String phone = request.get("phone");
//        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//        otpStorage.put(phone, otp);
//
//        smsService.sendOtp(phone, otp);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "OTP sent via MSG91");
//        return response;
//    }


    @PostMapping("/send-otp")
    public Map<String, String> sendOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(phone, otp);

        // Simulate sending SMS (in real case integrate with SMS API like Twilio or MSG91)
        System.out.println("Sending OTP to " + phone + ": " + otp);

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent");
        return response;
    }

    @PostMapping("/verify-otp")
    public Map<String, Object> verifyOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String enteredOtp = request.get("otp");
        String actualOtp = otpStorage.get(phone);

        Map<String, Object> response = new HashMap<>();
        if (enteredOtp != null && enteredOtp.equals(actualOtp)) {
            otpStorage.remove(phone); // Optional: Clear OTP after use
            response.put("status", "success");
            response.put("message", "OTP verified");
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid OTP");
        }

        return response;
    }
}
