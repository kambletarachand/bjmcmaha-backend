package com.bjmc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String phoneNumber;

    private String address;

    @NotBlank
    private String email;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContactDetails() {
        super();
    }

    public ContactDetails(Long id, @NotBlank String phoneNumber, String address, @NotBlank String email) {
        super();
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

	public void setContactInfo(String string) {
		// TODO Auto-generated method stub
		
	}

    // @Override
   
}
