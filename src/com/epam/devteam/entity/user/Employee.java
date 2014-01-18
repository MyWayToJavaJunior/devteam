package com.epam.devteam.entity.user;

import java.sql.Date;

/**
 * The <code>Employee</code> class extends <code>User</code> class and contains
 * unique fields for employees as qualification.
 * 
 * @date Jan 4, 2014
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.entity.user.User
 * @see com.epam.devteam.entity.user.UserRole
 */
public class Employee extends User {
    private static final long serialVersionUID = 1L;
    private String qualification;

    /**
     * Initializes a newly created {@code Employee} object.
     */
    public Employee() {
	super();
    }

    /**
     * Initializes a newly created {@code Employee} object with the given field
     * values.
     * 
     * @param id The user id.
     * @param email The user email.
     * @param password The user password.
     * @param registrationDate The date of registration.
     * @param role The role of the user.
     * @param active The status of the user.
     * @param firstName The user first name.
     * @param lastName The user last name.
     * @param birthDate The user birth date.
     * @param address The user address.
     * @param phone The contact phone number.
     * @param qualifiaction The employee qualification.
     */
    public Employee(int id, String email, String password,
	    Date registrationDate, UserRole role, boolean active,
	    String firstName, String lastName, Date birthDate, String address,
	    String phone, String qualification) {
	super(id, email, password, registrationDate, role, active, firstName,
		lastName, birthDate, address, phone);
	this.qualification = qualification;
    }

    /**
     * Returns the qualification field value.
     * 
     * @return The qualification.
     */
    public String getQualification() {
	return qualification;
    }

    /**
     * Sets the qualification field value.
     * 
     * @param qualification The qualification to set.
     */
    public void setQualification(String qualification) {
	this.qualification = qualification;
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
	    Employee otherEmployee = (Employee) obj;
	    return (this.getId() == otherEmployee.getId())
		    && (this.getEmail().equals(otherEmployee.getEmail()))
		    && (this.getPassword().equals(otherEmployee.getPassword()))
		    && (this.getRegistrationDate().equals(otherEmployee
			    .getRegistrationDate()))
		    && (this.getRole().equals(otherEmployee.getRole()))
		    && (this.isActive() == otherEmployee.isActive())
		    && (this.getFirstName()
			    .equals(otherEmployee.getFirstName()))
		    && (this.getLastName().equals(otherEmployee.getLastName()))
		    && (this.getBirthDate()
			    .equals(otherEmployee.getBirthDate()))
		    && (this.getAddress().equals(otherEmployee.getAddress()))
		    && (this.getPhone().equals(otherEmployee.getPhone()))
		    && (this.qualification.equals(otherEmployee.qualification));
	} else {
	    return false;
	}
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
	return super.hashCode()
		+ ((qualification == null) ? 0 : qualification.hashCode());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append(super.toString()).append(" qualification:")
		.append(qualification);
	return sb.toString();
    }
}
