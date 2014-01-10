/**
 * 
 */
package com.epam.devteam.db;

/**
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
