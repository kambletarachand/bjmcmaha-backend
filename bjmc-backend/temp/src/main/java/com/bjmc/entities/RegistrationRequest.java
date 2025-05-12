package com.bjmc.entities; // Ensure this matches your project structure

import com.bjmc.entities.ContactDetails;
import com.bjmc.entities.BjmcVisitor;

public class RegistrationRequest {
    private BjmcVisitor bjmcVisitor;
    private ContactDetails contactDetails;
    private String role;
    

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Getters and Setters
    public BjmcVisitor getSmartSocietyVisitor() {
        return bjmcVisitor;
    }

    public void setVisitorData(BjmcVisitor bjmcVisitor) {
        this.bjmcVisitor = bjmcVisitor;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
