/**
 * 
 */
package com.epam.devteam.db;

/**
 * @date Dec 15, 2013
 * @author anjey
 * 
 */
public enum DatabaseType {

    POSTGRESQL;
    
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
