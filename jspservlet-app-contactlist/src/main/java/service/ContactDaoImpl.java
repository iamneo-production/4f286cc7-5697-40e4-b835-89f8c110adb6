package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import utility.Database;
import model.Contact;
public class ContactDaoImpl implements Contactdao{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String QUERY_ALL = "SELECT * FROM contact";
	private static final String QUERY_BY_ID = "SELECT * FROM contact WHERE id = ?";
	private static final String QUERY_SAVE = "INSERT INTO contact(first_name, last_name, mobile_number, email_address, address) VALUES(?, ?, ?, ?, ?)";
	private static final String QUERY_UPDATE = "UPDATE contact SET first_name = ?, last_name = ?, mobile_number = ?, email_address = ?, address = ? WHERE id = ?";
	private static final String QUERY_DELETE_BY_ID = "DELETE FROM contact WHERE id = ?";

	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = new ArrayList<>();
		
		try (Connection connection = Database.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_ALL);
			ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(resultSet.getInt("id"));
				contact.setContactName(resultSet.getString("contact_name"));
				contact.setContactImage(resultSet.getString("contactImage"));
				contact.setcontactNumber(resultSet.getString("address"));
				contacts.add(contact);
			}
			
		} catch (Exception exception) {
			LOGGER.severe(exception.getMessage());
		}
		
		return contacts;
	}

	@Override
	public Optional<Contact> findById(long id) {
		ResultSet resultSet = null;
		
		try (Connection connection = Database.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_BY_ID);) {			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(resultSet.getInt("id"));
				contact.setContactName(resultSet.getString("contact_name"));
				contact.setContactImage(resultSet.getString("contactImage"));
				contact.setcontactNumber(resultSet.getString("address"));
				
				
				return Optional.ofNullable(contact);
			}			
		} catch (Exception exception) {
			LOGGER.severe(exception.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException sqlException) {
					LOGGER.severe(sqlException.getMessage());
				}
			}
		}
		
		return Optional.empty();
	}

	@Override
	public void addContact(Contact contact) {		
		try (Connection connection = Database.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_SAVE);) {	
			int counter = 1;
			statement.setString(counter++, contact.getContactName());
			statement.setString(counter++, contact.getContactNumber());
			statement.setString(counter++, contact.getContactImage());
			
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOGGER.severe(sqlException.getMessage());
		}
	}
	@Override
	public void viewContact(Contact contact) {		
		try (Connection connection = Database.getDBConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY_SAVE);) {	
			int counter = 1;
			statement.setString(counter++, contact.getContactName());
			statement.setString(counter++, contact.getContactNumber());
			statement.setString(counter++, contact.getContactImage());
			
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOGGER.severe(sqlException.getMessage());
		}
	}

	@Override
	public void updateContact(Contact contact) {
		try (Connection connection = Database.getDBConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);) {
			int counter = 1;
			statement.setString(counter++, contact.getContactName());
			statement.setString(counter++, contact.getContactNumber());
			statement.setString(counter++, contact.getContactImage());
			statement.setLong(counter++, contact.getId());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOGGER.severe(sqlException.getMessage());
		}
	}

	@Override
	public void deleteContact(long id) {
		try (Connection connection = Database.getDBConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_BY_ID);) {
			int counter = 1;
			statement.setLong(counter++, id);
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			LOGGER.severe(sqlException.getMessage());
		}
	}

}

    

