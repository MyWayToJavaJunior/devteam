/**
 * 
 */
package com.epam.devteam.entity.user;

import java.io.Serializable;
import java.sql.Date;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String password;
    private Date registrationDate;
    private UserRole role;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String phone;

    /**
     * Initializes a newly created {@code User} object with empty fields.
     */
    public User() {
	super();
    }

    /**
     * Initializes a newly created {@code User} object with the given field
     * values.
     * 
     * @param id The user id.
     * @param email The user email.
     * @param password The user password.
     * @param registrationDate The date of registration.
     * @param role The user role.
     * @param firstName The user first name.
     * @param lastName The user last name.
     * @param birthDate The user birth date.
     * @param address The user address.
     * @param phone The contact phone number
     */
    public User(Integer id, String email, String password,
	    Date registrationDate, UserRole role, boolean isActive,
	    String firstName, String lastName, Date birthDate, String address,
	    String phone) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.registrationDate = registrationDate;
	this.role = role;
	this.isActive = isActive;
	this.firstName = firstName;
	this.lastName = lastName;
	this.birthDate = birthDate;
	this.address = address;
	this.phone = phone;
    }

    /**
     * Returns the id field value.
     * 
     * @return The id.
     */
    public Integer getId() {
	return id;
    }

    /**
     * Sets the id field value.
     * 
     * @param id The id to set.
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Returns the email field value.
     * 
     * @return The email.
     */
    public String getEmail() {
	return email;
    }

    /**
     * Sets the email field value.
     * 
     * @param email The email to set.
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Returns the password field value.
     * 
     * @return The password.
     */
    public String getPassword() {
	return password;
    }

    /**
     * Sets the password field value.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Returns the registrationDate field value.
     * 
     * @return The registrationDate.
     */
    public Date getRegistrationDate() {
	return registrationDate;
    }

    /**
     * Sets the registrationDate field value.
     * 
     * @param registrationDate The registrationDate to set.
     */
    public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
    }

    /**
     * Returns the user role field value.
     * 
     * @return The user role.
     */
    public UserRole getRole() {
	return role;
    }

    /**
     * Sets the role field value.
     * 
     * @param role The role to set.
     */
    public void setRole(UserRole role) {
	this.role = role;
    }

    /**
     * Returns the isActive field value.
     * 
     * @return The isActive.
     */
    public boolean isActive() {
	return isActive;
    }

    /**
     * Sets the isActive field value.
     * 
     * @param isActive The isActive to set.
     */
    public void setActive(boolean isActive) {
	this.isActive = isActive;
    }

    /**
     * Returns the firstName field value.
     * 
     * @return The firstName.
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * Sets the firstName field value.
     * 
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    /**
     * Returns the lastName field value.
     * 
     * @return The lastName.
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * Sets the lastName field value.
     * 
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    /**
     * Returns the birthDate field value.
     * 
     * @return The birthDate.
     */
    public Date getBirthDate() {
	return birthDate;
    }

    /**
     * Sets the birthDate field value.
     * 
     * @param birthDate The birthDate to set.
     */
    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    /**
     * Returns the address field value.
     * 
     * @return The address.
     */
    public String getAddress() {
	return address;
    }

    /**
     * Sets the address field value.
     * 
     * @param address The address to set.
     */
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * Returns the phone field value.
     * 
     * @return The phone.
     */
    public String getPhone() {
	return phone;
    }

    /**
     * Sets the phone field value.
     * 
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
	this.phone = phone;
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
	    User otherUser = (User) obj;
	    return (this.id.equals(otherUser.id))
		    && (this.email.equals(otherUser.email))
		    && (this.password.equals(otherUser.password))
		    && (this.registrationDate
			    .equals(otherUser.registrationDate))
		    && (this.role.equals(otherUser.role))
		    && (this.isActive == otherUser.isActive)
		    && (this.firstName.equals(otherUser.firstName))
		    && (this.lastName.equals(otherUser.lastName))
		    && (this.birthDate.equals(otherUser.birthDate))
		    && (this.address.equals(otherUser.address))
		    && (this.phone.equals(otherUser.phone));

	} else {
	    return false;
	}
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
	return (int) (31 * id.hashCode()
		+ ((email == null) ? 0 : email.hashCode()) + ((password == null) ? 0
		    : password.hashCode()))
		+ ((registrationDate == null) ? 0 : registrationDate.hashCode())
		+ ((role == null) ? 0 : role.hashCode())
		+ Boolean.valueOf(isActive).hashCode()
		+ ((firstName == null) ? 0 : firstName.hashCode())
		+ ((lastName == null) ? 0 : lastName.hashCode())
		+ ((birthDate == null) ? 0 : birthDate.hashCode())
		+ ((address == null) ? 0 : address.hashCode())
		+ ((phone == null) ? 0 : phone.hashCode());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("user:").append(lastName).append(" ").append(firstName)
		.append(" role:").append(role).append(" birthdate:")
		.append(birthDate).append(" email:").append(email)
		.append(" address:").append(address).append(" phone:")
		.append(phone).append(" registered:").append(registrationDate)
		.append(" id:").append(id);
	return sb.toString();
    }
}
