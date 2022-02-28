package service;
import java.util.List;
import java.util.Optional;
import model.Contact;

public interface contactservice {
    
    List<Contact> findAll();

    Optional<Contact> findById(long id);

    void saveContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(long id);
    void viewContact(Contact c);

}

    

