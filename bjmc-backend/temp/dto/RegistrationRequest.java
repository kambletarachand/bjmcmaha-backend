package com.bjmc.dto;

import com.bjmc.entities.ContactDetails;
import com.bjmc.entities.BjmcVisitor;
public class RegistrationRequest {
    private ContactDetails contactDetails;
    private String password;
    private String role;
	public ContactDetails getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "RegistrationRequest [contactDetails=" + contactDetails + ", password=" + password + ", role=" + role
				+ "]";
	}
	public void setRole(String role) {
		this.role = role;
	}

    // Getters and setters
}
