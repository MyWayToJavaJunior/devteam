/**
 * 
 */
package com.epam.devteam.entity;

/**
 * @date Jan 4, 2014 	
 * @author anjey
 *
 */
public enum UserRole {
    ADMINISTRATOR, MANAGER, DEVELOPER, CUSTOMER;
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
