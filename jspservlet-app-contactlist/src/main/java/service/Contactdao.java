package service;
import model.Contact;
import java.util.List;
import java.util.Optional;

    public interface Contactdao {
	
        List<Contact> findAll();
    
        Optional<Contact> findById(long id);
        void viewContact(Contact c);
    
        void addContact(Contact c);
    
        void updateContact(Contact c);
    
        void deleteContact(long id);
        
    }
    

