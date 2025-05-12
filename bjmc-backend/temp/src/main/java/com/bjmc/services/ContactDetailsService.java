package com.bjmc.services;
import com.bjmc.entities.ContactDetails;
import java.util.List;
import java.util.Optional;

public interface ContactDetailsService {
    ContactDetails saveOrUpdateContactDetails(ContactDetails contactDetails);
    Optional<ContactDetails> getContactDetailsById(Long id);
    void deleteContactDetails(Long id);
    List<ContactDetails> getAllContactDetails();
    ContactDetails createContactDetails(ContactDetails contactDetails);
    ContactDetails updateContactDetails(Long id, ContactDetails contactDetails);
}
