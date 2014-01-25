/**
 * 
 */
package com.epam.devteam.entity.user;

import java.io.Serializable;
import java.sql.Date;

/**
 * The <code>User</code> provides basic fields and methods for all users.
 * 
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.entity.user.UserRole
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private String password;
    private Date registrationDate;
    private UserRole role;
    private boolean active;
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
     * @param role The role of the user.
     * @param active The status of the user.
     * @param firstName The user first name.
     * @param lastName The user last name.
     * @param birthDate The user birth date.
     * @param address The user address.
     * @param phone The contact phone number.
     */
    public User(Integer id, String email, String password,
	    Date registrationDate, UserRole role, boolean active,
	    String firstName, String lastName, Date birthDate, String address,
	    String phone) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.registrationDate = registrationDate;
	this.role = role;
	this.active = active;
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
    public int getId() {
	return id;
    }

    /**
     * Sets the id field value.
     * 
     * @param id The id to set.
     */
    public void setId(int id) {
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
	return active;
    }

    /**
     * Sets the isActive field value.
     * 
     * @param isActive The isActive to set.
     */
    public void setActive(boolean active) {
	this.active = active;
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
     * Returns a hash code value for the object.
     * 
     * @param The object's hash code.
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (active ? 1231 : 1237);
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result
		+ ((birthDate == null) ? 0 : birthDate.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + id;
	result = prime * result
		+ ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((phone == null) ? 0 : phone.hashCode());
	result = prime
		* result
		+ ((registrationDate == null) ? 0 : registrationDate.hashCode());
	result = prime * result + ((role == null) ? 0 : role.hashCode());
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
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (active != other.active)
	    return false;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	if (birthDate == null) {
	    if (other.birthDate != null)
		return false;
	} else if (!birthDate.equals(other.birthDate))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (firstName == null) {
	    if (other.firstName != null)
		return false;
	} else if (!firstName.equals(other.firstName))
	    return false;
	if (id != other.id)
	    return false;
	if (lastName == null) {
	    if (other.lastName != null)
		return false;
	} else if (!lastName.equals(other.lastName))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (phone == null) {
	    if (other.phone != null)
		return false;
	} else if (!phone.equals(other.phone))
	    return false;
	if (registrationDate == null) {
	    if (other.registrationDate != null)
		return false;
	} else if (!registrationDate.equals(other.registrationDate))
	    return false;
	if (role != other.role)
	    return false;
	return true;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("User id:").append(id).append(" email:").append(email)
		.append(" registered:").append(registrationDate)
		.append(" status:").append(active).append(" role:")
		.append(role).append(" name:").append(lastName).append(" ")
		.append(firstName).append(" birthdate:").append(birthDate)
		.append(" address:").append(address).append(" phone:")
		.append(phone);
	return sb.toString();
    }
}
