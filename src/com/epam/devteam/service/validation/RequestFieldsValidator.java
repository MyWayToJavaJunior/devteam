package com.epam.devteam.service.validation;

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
    public static boolean equalsNull(String... values) {
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
	    if (values[i] == null) {
		result = false;
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
}
