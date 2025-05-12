package com.bjmc.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne(targetEntity = BjmcVisitor.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "visitor_id")
    private BjmcVisitor smartSocietyVisitor;

    private Date expiryDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BjmcVisitor getVisitor() {
        return smartSocietyVisitor;
    }

    public void setVisitor(BjmcVisitor smartSocietyVisitor) {
        this.smartSocietyVisitor = smartSocietyVisitor;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
