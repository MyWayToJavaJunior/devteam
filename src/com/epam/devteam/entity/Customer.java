package com.epam.devteam.entity;

import java.sql.Date;

/**
 * @date Jan 4, 2014
 * @author Andrey Kovalskiy
 */
public class Customer extends User {

    private static final long serialVersionUID = 1L;
    private String company;
    private String position;

    /**
     * Initializes a newly created {@code Customer} object.
     */
    public Customer() {
	super();
    }

    /**
     * Initializes a newly created {@code Customer} object with the given
     * values.
     * 
     * @param id The user id.
     * @param email The user email.
     * @param password The user password.
     * @param registrationDate The date of registration.
     * @param firstName The user first name.
     * @param lastName The user last name.
     * @param birthDate The user birth date.
     * @param phone The contact phone number
     * @param company The company where the customer works.
     * @param position The position of the customer.
     */
    public Customer(Integer id, String email, String password,
	    Date registrationDate, UserRole role, String firstName,
	    String lastName, Date birthDate, String address, String phone,
	    String company, String position) {
	super(id, email, password, registrationDate, role, firstName, lastName,
		birthDate, address, phone);
	this.company = company;
	this.position = position;
    }

    /**
     * Returns the company field value.
     * 
     * @return The company.
     */
    public String getCompany() {
	return company;
    }

    /**
     * Sets the company field value.
     * 
     * @param company The company to set.
     */
    public void setCompany(String company) {
	this.company = company;
    }

    /**
     * Returns the position field value.
     * 
     * @return The position.
     */
    public String getPosition() {
	return position;
    }

    /**
     * Sets the position field value.
     * 
     * @param position The position to set.
     */
    public void setPosition(String position) {
	this.position = position;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj The reference object with which to compare.
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (this.getClass() == obj.getClass()) {
	    Customer otherCustomer = (Customer) obj;
	    return (this.getId().equals(otherCustomer.getId()))
		    && (this.getEmail().equals(otherCustomer.getEmail()))
		    && (this.getPassword().equals(otherCustomer.getPassword()))
		    && (this.getRegistrationDate().equals(otherCustomer
			    .getRegistrationDate()))
		    && (this.getRole().equals(otherCustomer.getRole()))
		    && (this.getFirstName()
			    .equals(otherCustomer.getFirstName()))
		    && (this.getLastName().equals(otherCustomer.getLastName()))
		    && (this.getBirthDate()
			    .equals(otherCustomer.getBirthDate()))
		    && (this.getAddress().equals(otherCustomer.getAddress()))
		    && (this.getPhone().equals(otherCustomer.getPhone()))
		    && (this.company.equals(otherCustomer.company))
		    && (this.position.equals(otherCustomer.position));
	} else {
	    return false;
	}
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
	return super.hashCode() + ((company == null) ? 0 : company.hashCode())
		+ ((position == null) ? 0 : position.hashCode());
    }
}
