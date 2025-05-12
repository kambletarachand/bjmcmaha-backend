package com.bjmc.services;



import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    private final String AUTH_KEY = "YOUR_MSG91_AUTH_KEY";
    private final String TEMPLATE_ID = "YOUR_TEMPLATE_ID"; // from MSG91
    private final String SENDER_ID = "YOUR_SENDER_ID";

    public void sendOtp(String phone, String otp) {
        String mobile = "91" + phone; // Indian format

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://control.msg91.com/api/v5/otp";

        Map<String, String> payload = new HashMap<>();
        payload.put("authkey", AUTH_KEY);
        payload.put("template_id", TEMPLATE_ID);
        payload.put("mobile", mobile);
        payload.put("otp", otp);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("MSG91 Response: " + response.getBody());
    }
}
