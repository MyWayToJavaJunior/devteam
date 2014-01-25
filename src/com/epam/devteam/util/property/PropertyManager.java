/**
 * 
 */
package com.epam.devteam.util.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @date Dec 18, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class PropertyManager {

    private static final Logger LOGGER = Logger
	    .getLogger(PropertyManager.class);
    private static volatile PropertyManager instance = null;
    private Properties properties = null;

    /**
     * Is used to get property manager implementation instance. Initialization
     * is to be during the first access.
     * 
     * @return The property manager instance.
     * @throws PropertyManagerException If initialization fails.
     */
    public static PropertyManager getInstance() throws PropertyManagerException {
	PropertyManager localInstance = instance;
	if (localInstance == null) {
	    synchronized (PropertyManager.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new PropertyManager();
		    instance.init();
		}
	    }
	}
	return instance;
    }

    /**
     * Is used to initialize property manager instance.
     * 
     * @throws PropertyManagerException If the property file can not be loaded.
     */
    private void init() throws PropertyManagerException {
	properties = new Properties();
	InputStream stream = PropertyManager.class.getClassLoader()
		.getResourceAsStream("properties.properties");
	if (stream == null) {
	    LOGGER.error("Property file cannot be found.");
	    throw new PropertyManagerException();
	}
	try {
	    properties.load(stream);
	} catch (IOException e) {
	    LOGGER.error("Can not load property file.");
	    throw new PropertyManagerException(e);
	}
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * string.
     * 
     * @param key The property key.
     * @return The required property.
     * @throws PropertyManagerException If property is not found.
     */
    public String getString(String key) throws PropertyManagerException {
	String value = null;
	value = properties.getProperty(key);
	if (value == null) {
	    LOGGER.warn("Value " + key + " is not defined.");
	    throw new PropertyManagerException();
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * a string. If property value is not found or has wrong format method will
     * return default value.
     * 
     * @param key The property key.
     * @param defaultValue The value to be returned if something wrong with
     *            property value.
     * @return The required property.
     */
    public String getString(String key, String defaultValue) {
	String value = null;
	try {
	    value = getString(key);
	} catch (PropertyManagerException e) {
	    value = defaultValue;
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * a long number.
     * 
     * @param key The property key.
     * @return The required property.
     * @throws PropertyManagerException If property is not found or has wrong
     *             format.
     */
    public long getLong(String key) throws PropertyManagerException {
	long value = 0;
	String temp = getString(key);
	try {
	    value = Long.parseLong(temp);
	} catch (NumberFormatException e) {
	    LOGGER.warn("Wrong " + key + " value format.");
	    throw new PropertyManagerException(e);
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * a long number. If property value is not found or has wrong format method
     * will return default value.
     * 
     * @param key The property key.
     * @param defaultValue The value to be returned if something wrong with
     *            property value.
     * @return The required property.
     */
    public long getLong(String key, long defaultValue) {
	long value = 0;
	try {
	    value = getLong(key);
	} catch (PropertyManagerException e) {
	    value = defaultValue;
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * an integer number.
     * 
     * @param key The property key.
     * @return The required property.
     * @throws PropertyManagerException If property is not found or has wrong
     *             format.
     */
    public int getInt(String key) throws PropertyManagerException {
	int value = 0;
	String temp = getString(key);
	try {
	    value = Integer.parseInt(temp);
	} catch (NumberFormatException e) {
	    LOGGER.warn("Wrong " + key + " value format.");
	    throw new PropertyManagerException(e);
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * an integer number. If property value is not found or has wrong format
     * method will return default value.
     * 
     * @param key The property key.
     * @param defaultValue The value to be returned if something wrong with
     *            property value.
     * @return The required property.
     */
    public int getInt(String key, int defaultValue) {
	int value = 0;
	try {
	    value = getInt(key);
	} catch (PropertyManagerException e) {
	    value = defaultValue;
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * double number.
     * 
     * @param key The property key.
     * @return The required property.
     * @throws PropertyManagerException If property is not found or has wrong
     *             format.
     */
    public double getDouble(String key) throws PropertyManagerException {
	double value = 0;
	String temp = getString(key);
	try {
	    value = Double.parseDouble(temp);
	} catch (NumberFormatException e) {
	    LOGGER.warn("Wrong " + key + " value format.");
	    throw new PropertyManagerException(e);
	}
	return value;
    }

    /**
     * Is used to get the property with specified key. Returns property value as
     * a double number. If property value is not found or has wrong format
     * method will return default value.
     * 
     * @param key The property key.
     * @param defaultValue The value to be returned if something wrong with
     *            property value.
     * @return The required property.
     */
    public double getDouble(String key, double defaultValue) {
	double value = 0;
	try {
	    value = getLong(key);
	} catch (PropertyManagerException e) {
	    value = defaultValue;
	}
	return value;
    }

}
