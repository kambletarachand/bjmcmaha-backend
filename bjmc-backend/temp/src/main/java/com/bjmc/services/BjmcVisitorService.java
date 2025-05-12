package com.bjmc.services;

import com.bjmc.dao.ContactDetailsRepository;
import com.bjmc.dao.BjmcVisitorRepository;
import com.bjmc.dto.VisitorRegistrationRequest;
import com.bjmc.entities.ContactDetails;
import com.bjmc.entities.BjmcVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BjmcVisitorService {

    @Autowired
    private BjmcVisitorRepository smartSocietyVisitorRepository;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  
    
    public Optional<BjmcVisitor> findByEmail(String email) {
        System.out.println("Finding visitor by email: " + email);
        Optional<BjmcVisitor> result = smartSocietyVisitorRepository.findByEmail(email);
        System.out.println("Find result: " + result);
        return result;
    }

    public BjmcVisitor save(BjmcVisitor smartSocietyVisitor) {
        System.out.println("Saving visitor data: " + smartSocietyVisitor);
        if (smartSocietyVisitor.getRole() == null) {
            smartSocietyVisitor.setRole("user");
            System.out.println("Assigned default role 'user' to visitor: " + smartSocietyVisitor.getEmail());
        }
        return smartSocietyVisitorRepository.save(smartSocietyVisitor);
    }
    
    public boolean checkEmailVerificationStatus(String email) {
        Optional<BjmcVisitor> smartSocietyVisitor = smartSocietyVisitorRepository.findByEmail(email);
        return smartSocietyVisitor.map(BjmcVisitor::isVerified).orElse(false);
    }

//    public Optional<SmartSocietyVisitor> registerVisitor(SmartSocietyVisitor smartSocietyVisitor) {
//        smartSocietyVisitor.setPassword(passwordEncoder.encode(smartSocietyVisitor.getPassword()));
//        smartSocietyVisitor.setVerified(false); // Default to not verified
//        smartSocietyVisitor.setVerificationToken(generateVerificationToken()); // Method to create a token
//        smartSocietyVisitorRepository.save(smartSocietyVisitor);
//        emailService.sendVerificationEmail(smartSocietyVisitor.getEmail(), smartSocietyVisitor.getVerificationToken());
//        return Optional.of(smartSocietyVisitor);
//    }
    


    public Optional<BjmcVisitor> registerVisitor(VisitorRegistrationRequest request) {
        BjmcVisitor smartSocietyVisitor = new BjmcVisitor();
        smartSocietyVisitor.setEmail(request.getEmail());
        smartSocietyVisitor.setPassword(passwordEncoder.encode(request.getPassword()));
        smartSocietyVisitor.setRole(request.getRole());
        smartSocietyVisitor.setVerified(false);
        smartSocietyVisitor.setVerificationToken(generateVerificationToken());

        // Create contact details from request
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmail(request.getEmail());
        contactDetails.setPhoneNumber(request.getPhoneNumber());
        contactDetails.setAddress(request.getAddress());

        contactDetails = contactDetailsRepository.save(contactDetails);
        smartSocietyVisitor.setContactDetails(contactDetails);

        smartSocietyVisitorRepository.save(smartSocietyVisitor);
        emailService.sendVerificationEmail(smartSocietyVisitor.getEmail(), smartSocietyVisitor.getVerificationToken());

        return Optional.of(smartSocietyVisitor);
    }

    public Optional<BjmcVisitor> loginVisitor(String email, String password) {
        Optional<BjmcVisitor> smartSocietyVisitorOpt = smartSocietyVisitorRepository.findByEmail(email);
        if (smartSocietyVisitorOpt.isPresent()) {
            BjmcVisitor smartSocietyVisitor = smartSocietyVisitorOpt.get();
            if (passwordEncoder.matches(password, smartSocietyVisitor.getPassword()) && smartSocietyVisitor.isVerified()) {
                return Optional.of(smartSocietyVisitor);
            }
        }
        return Optional.empty();
    }

    public boolean verifyEmail(String token) {
        Optional<BjmcVisitor> smartSocietyVisitorOpt = smartSocietyVisitorRepository.findByVerificationToken(token);
        if (smartSocietyVisitorOpt.isPresent()) {
            BjmcVisitor smartSocietyVisitor = smartSocietyVisitorOpt.get();
            smartSocietyVisitor.setVerified(true);
            smartSocietyVisitor.setVerificationToken(null); // Invalidate the token
            smartSocietyVisitorRepository.save(smartSocietyVisitor);
            return true;
        }
        return false;
    }

    // Other methods...

    private String generateVerificationToken() {
        // Generate a unique verification token (e.g., UUID)
        return UUID.randomUUID().toString();
    }// Other service methods (e.g., update visitor data, get visitor by ID) can be added here



}
