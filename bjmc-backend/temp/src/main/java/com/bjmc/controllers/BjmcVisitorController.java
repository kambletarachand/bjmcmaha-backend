package com.bjmc.controllers;

import com.bjmc.dto.VisitorRegistrationRequest;
import com.bjmc.entities.ContactDetails;
import com.bjmc.entities.EmailRequest;
import com.bjmc.entities.LoginRequest;
import com.bjmc.dto.RegistrationRequest;
import com.bjmc.entities.BjmcVisitor;
import com.bjmc.services.ContactDetailsService;
import com.bjmc.services.BjmcVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin("http://localhost:3000")
public class BjmcVisitorController {

    @Autowired
    private BjmcVisitorService bjmcVisitorService;

    @Autowired
    private ContactDetailsService contactDetailsService;

    // Register visitor with registration request
//    @PostMapping("/register")
//    public ResponseEntity<SmartSocietyVisitor> registerVisitor(@RequestBody RegistrationRequest registrationRequest) {
//        // Validate registrationRequest before proceeding
//        if (registrationRequest == null || registrationRequest.getSmartSocietyVisitor() == null || registrationRequest.getContactDetails() == null) {
//            return ResponseEntity.badRequest().build(); // Return a bad request response if the request is invalid
//        }
//
//        // Create the VisitorRegistrationRequest object from RegistrationRequest
//        VisitorRegistrationRequest visitorRegistrationRequest = new VisitorRegistrationRequest();
//        
//        // Mapping values from RegistrationRequest to VisitorRegistrationRequest
//        visitorRegistrationRequest.setEmail(registrationRequest.getSmartSocietyVisitor().getEmail());
//        visitorRegistrationRequest.setPassword(registrationRequest.getSmartSocietyVisitor().getPassword());
//        visitorRegistrationRequest.setRole(registrationRequest.getSmartSocietyVisitor().getRole());
//        visitorRegistrationRequest.setPhoneNumber(registrationRequest.getContactDetails().getPhoneNumber());
//        visitorRegistrationRequest.setAddress(registrationRequest.getContactDetails().getAddress());
//
//        // Call service to register visitor
//        SmartSocietyVisitor registeredVisitor = bjmcVisitorService.registerVisitor(visitorRegistrationRequest)
//                .orElseThrow(() -> new RuntimeException("Registration failed"));
//
//        return ResponseEntity.ok(registeredVisitor);
//    }

    @PostMapping("/register")
    public ResponseEntity<BjmcVisitor> registerVisitor(@RequestBody RegistrationRequest registrationRequest) {
    	System.out.println("registrationRequest "+registrationRequest);
    	
        // Validate input
        if (registrationRequest == null || registrationRequest.getContactDetails() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Extract contact details
        ContactDetails contact = registrationRequest.getContactDetails();

        // Prepare service DTO
        VisitorRegistrationRequest visitorRequest = new VisitorRegistrationRequest();
        visitorRequest.setEmail(contact.getEmail());
        visitorRequest.setPhoneNumber(contact.getPhoneNumber());
        visitorRequest.setAddress(contact.getAddress());
        visitorRequest.setPassword(registrationRequest.getPassword());
        visitorRequest.setRole(registrationRequest.getRole());

        // Call service
        BjmcVisitor registeredVisitor = bjmcVisitorService
                .registerVisitor(visitorRequest)
                .orElseThrow(() -> new RuntimeException("Registration failed"));

        return ResponseEntity.ok(registeredVisitor);
    }

    // Login visitor
    @PostMapping("/login")
    public ResponseEntity<String> loginVisitor(@RequestParam String email, @RequestParam String password) {
        Optional<BjmcVisitor> smartSocietyVisitor = bjmcVisitorService.loginVisitor(email, password);
        if (smartSocietyVisitor.isPresent()) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(400).body("Invalid credentials or email not verified.");
        }
    }

    @GetMapping("/{email}")
    public Optional<BjmcVisitor> getVisitorByEmail(@PathVariable String email) {
    	System.out.println("Getting details of "+email);
        return bjmcVisitorService.findByEmail(email);
    }

    // Check email verification status
    @GetMapping("/verify/{email}/status")
    public ResponseEntity<?> checkEmailVerificationStatus(@PathVariable String email) {
        boolean isVerified = bjmcVisitorService.checkEmailVerificationStatus(email);
        return ResponseEntity.ok().body("{\"verified\":" + isVerified + "}");
    }

    // Send verification email
    @PostMapping("/send-verification")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody EmailRequest emailRequest) {
        // Logic to send verification email
        return ResponseEntity.ok("Verification email sent to " + emailRequest.getEmail());
    }

    // Verify email with token
    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean isVerified = bjmcVisitorService.verifyEmail(token);
        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification token.");
    }
    @PostMapping("/request/login")
    public ResponseEntity<?> loginVisitor(@RequestBody LoginRequest loginRequest) {
        Optional<BjmcVisitor> smartSocietyVisitor = bjmcVisitorService.loginVisitor(loginRequest.getEmail(), loginRequest.getPassword());
        if (smartSocietyVisitor.isPresent()) {
            return ResponseEntity.ok(smartSocietyVisitor.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or email not verified.");
        }
    }
    @GetMapping("/test")
    public void test() {System.out.println("Controller Test");}


}
