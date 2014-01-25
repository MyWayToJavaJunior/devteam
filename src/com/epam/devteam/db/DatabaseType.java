/**
 * 
 */
package com.epam.devteam.db;

/**
 * The <code>DatabaseType</code> defines with what database types application
 * can work.
 * 
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public enum DatabaseType {

    POSTGRESQL;

    @Override
    public String toString() {
	return name().toLowerCase();
    }
}
