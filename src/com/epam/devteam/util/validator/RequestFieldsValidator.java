package com.epam.devteam.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import com.epam.devteam.util.property.PropertyManager;
import com.epam.devteam.util.property.PropertyManagerException;

/**
 * The <code>FieldsValidator</code> to check fields values at null pointer
 * 
 * @date Jan 16, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class RequestFieldsValidator {
    private final static Logger LOGGER = Logger
	    .getLogger(RequestFieldsValidator.class);

    /**
     * Is used to check whether values are null or not.
     * 
     * @param values The values to check.
     * @return true if there is at least one null value, false otherwise.
     */
    public static boolean equalNull(String... values) {
	boolean result = false;
	for (int i = 0; i < values.length; i++) {
	    if (values[i] == null) {
		result = true;
		break;
	    }
	}
	return result;
    }

    /**
     * Is used to check whether values are null or not.
     * 
     * @param values The values to check.
     * @return true if there is at least one empty value, false otherwise.
     */
    public static boolean empty(String... values) {
	boolean result = false;
	for (int i = 0; i < values.length; i++) {
	    if (values[i].isEmpty()) {
		result = true;
		break;
	    }
	}
	return result;
    }

    /**
     * Is used to check whether values length is not more than max available
     * length.
     * 
     * @param values The values to check.
     * @return True if all values length is valid.
     * @throws ValidationException
     */
    public static boolean lengthValid(FieldType type, String... values)
	    throws ValidationException {
	String key;
	boolean result = true;
	int maxLength = 0;
	switch (type) {
	case INPUT_TEXT:
	    key = "validation.fields.maxLength.inputText";
	    break;
	case TEXTAREA:
	    key = "validation.fields.maxLength.textarea";
	    break;
	default:
	    LOGGER.warn("Unknown field type.");
	    throw new ValidationException();
	}
	try {
	    maxLength = PropertyManager.getInstance().getInt(key);
	} catch (PropertyManagerException e) {
	    LOGGER.warn("Input field length cannot be initialized.");
	    throw new ValidationException(e);
	}
	for (int i = 0; i < values.length; i++) {
	    if (values[i].length() > maxLength) {
		result = false;
		break;
	    }
	}
	return result;
    }

    /**
     * Is used to check whether email value is valid.
     * 
     * @param email The user email.
     * @return True if email is valid, false otherwise.
     * @throws ValidationException If property manager cannot be taken or email
     *             pattern is not valid
     */
    public static boolean emailValid(String email) throws ValidationException {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String emailRegex;
	try {
	    propertyManager = PropertyManager.getInstance();
	    emailRegex = propertyManager.getString("validation.email");
	} catch (PropertyManagerException e) {
	    LOGGER.debug("Email regex cannot be taken.");
	    throw new ValidationException(e);
	}
	try {
	    pattern = Pattern.compile(emailRegex);
	} catch (PatternSyntaxException e) {
	    LOGGER.debug("Email pattern cannot be compiled.");
	    throw new ValidationException(e);
	}
	matcher = pattern.matcher(email);
	if (!matcher.matches()) {
	    LOGGER.debug("User email is not valid.");
	    return false;
	}
	return true;
    }

    /**
     * Is used to check whether password value is valid.
     * 
     * @param password The user password to validate.
     * @return True if password is valid, false otherwise.
     * @throws ValidationException If property manager cannot be taken or email
     *             pattern is not valid
     */
    public static boolean passwordValid(String password)
	    throws ValidationException {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String passwordRegex;
	try {
	    propertyManager = PropertyManager.getInstance();
	    passwordRegex = propertyManager.getString("validation.password");
	} catch (PropertyManagerException e) {
	    LOGGER.debug("Password regex cannot be taken.");
	    throw new ValidationException(e);
	}
	try {
	    pattern = Pattern.compile(passwordRegex);
	} catch (PatternSyntaxException e) {
	    LOGGER.debug("Password pattern cannot be compiled.");
	    throw new ValidationException(e);
	}
	matcher = pattern.matcher(password);
	if (!matcher.matches()) {
	    LOGGER.debug("User password is not valid.");
	    return false;
	}
	return true;
    }
}
