package utility;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.validator.Validator;
import model.Contact;


public class ContactValidator implements Validator<Contact>{
	@Override
	public Validation validate(Contact contact) {
	
        Validation validation = new Validation();
		Map<String, String> errors = new HashMap<>();
		
		
		if (contact.getContactName() == null || contact.getContactName().isEmpty())
			errors.put("ContactName", " Name must not be empty.");
		
		if (contact.getContactNumber() == null || contact.getContactNumber().isEmpty())
			errors.put("mobileNumber", "Mobile Number must not be empty.");
		
		//if (Contact.getContactImage() != null && !contact.getContactImage().isEmpty()) {
			//errors.put("contactImage", "ContactImage Address is invalid.");
		//}
		
		if (contact.getId()==0)
			errors.put("ContactId", "Contact Id must not be empty.");
		
		validation.setErrors(errors);
		
		return validation;
	
	
	
    
}
}
