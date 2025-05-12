package com.bjmc.dao;



import com.bjmc.entities.BjmcVisitor;
import com.bjmc.entities.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findBySmartSocietyVisitor(BjmcVisitor visitor);
}
