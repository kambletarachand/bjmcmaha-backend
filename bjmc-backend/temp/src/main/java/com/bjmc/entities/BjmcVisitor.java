package com.bjmc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class BjmcVisitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;
    

    @NotBlank
    private String password; // Encrypted password



    private boolean verified;

    private String role;

    private String verificationToken; // Token for email verification

    @OneToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactDetails contactDetails;

    

    public BjmcVisitor() {
        super();
        this.verificationToken = UUID.randomUUID().toString(); // Generate a verification token
    }

    public BjmcVisitor(Long id, @NotBlank String email, String password, boolean verified, String role, String verificationToken, ContactDetails contactDetails) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;

        this.verified = verified;
        this.role = role;
        this.verificationToken = verificationToken;
        this.contactDetails = contactDetails;
        
    }

    // Getters and setters for all fields...

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	
	public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }
}
