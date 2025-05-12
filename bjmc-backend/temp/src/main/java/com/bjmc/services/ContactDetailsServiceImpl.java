package com.bjmc.services;

import com.bjmc.entities.ContactDetails;
import com.bjmc.dao.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;



    @Override
    public Optional<ContactDetails> getContactDetailsById(Long id) {
        return contactDetailsRepository.findById(id);
    }

    @Override
    public void deleteContactDetails(Long id) {
        contactDetailsRepository.deleteById(id);
    }

    @Override
    public List<ContactDetails> getAllContactDetails() {
        return contactDetailsRepository.findAll();
    }

    @Override
    public ContactDetails createContactDetails(ContactDetails contactDetails) {
        return contactDetailsRepository.save(contactDetails);
    }

    @Override
    public ContactDetails updateContactDetails(Long id, ContactDetails contactDetails) {
        Optional<ContactDetails> existingContactDetails = contactDetailsRepository.findById(id);
        if (existingContactDetails.isPresent()) {
            contactDetails.setId(id); // Ensure the ID is set for update
            return contactDetailsRepository.save(contactDetails);
        } else {
            throw new EntityNotFoundException("ContactDetails not found with id: " + id);
        }
    }

    @Override
    public ContactDetails saveOrUpdateContactDetails(@Valid ContactDetails contactDetails) {
        if (contactDetails.getId() == null || contactDetails.getId() <= 0) {
            contactDetails.setId(1L); // Default ID set to 1
        }
        return contactDetailsRepository.save(contactDetails);
    }

}
