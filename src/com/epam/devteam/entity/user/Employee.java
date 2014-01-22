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
     * Returns a hash code value for the object.
     * 
     * @param The object's hash code.
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((qualification == null) ? 0 : qualification.hashCode());
	return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj The reference object with which to compare.
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Employee other = (Employee) obj;
	if (qualification == null) {
	    if (other.qualification != null)
		return false;
	} else if (!qualification.equals(other.qualification))
	    return false;
	return true;
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
