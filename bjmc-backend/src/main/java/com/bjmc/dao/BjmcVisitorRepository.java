package com.bjmc.dao;

import com.bjmc.entities.BjmcVisitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BjmcVisitorRepository extends JpaRepository<BjmcVisitor, Long> {
    Optional<BjmcVisitor> findByEmail(String email);
    Optional<BjmcVisitor> findByVerificationToken(String token);
}
