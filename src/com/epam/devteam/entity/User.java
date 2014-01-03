/**
 * 
 */
package com.epam.devteam.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @date Dec 15, 2013
 * @author Andrey Kovalskiy
 * 
 */
public class User implements Serializable {
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthdate;
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
     * @param id the user id.
     * @param email the user email.
     * @param password the user password.
     * @param firstname the user first name.
     * @param lastname the user last name.
     * @param patronymic the user patronymic.
     * @param birthdate the user birthdate.
     */
    public User(long id, String email, String password, String firstname,
	    String lastname, String patronymic, Date birthdate) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.firstname = firstname;
	this.lastname = lastname;
	this.patronymic = patronymic;
	this.birthdate = birthdate;
    }

    /**
     * Returns the id field value.
     * 
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Sets the id field value.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * Returns the email field value.
     * 
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * Sets the email field value.
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Returns the password field value.
     * 
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Sets the password field value.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Returns the firstname field value.
     * 
     * @return the firstname
     */
    public String getFirstname() {
	return firstname;
    }

    /**
     * Sets the firstname field value.
     * 
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    /**
     * Returns the lastname field value.
     * 
     * @return the lastname
     */
    public String getLastname() {
	return lastname;
    }

    /**
     * Sets the lastname field value.
     * 
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    /**
     * Returns the patronymic field value.
     * 
     * @return the patronymic
     */
    public String getPatronymic() {
	return patronymic;
    }

    /**
     * Sets the patronymic field value.
     * 
     * @param patronymic the patronymic to set
     */
    public void setPatronymic(String patronymic) {
	this.patronymic = patronymic;
    }

    /**
     * Returns the birthdate field value.
     * 
     * @return the birthdate
     */
    public Date getBirthdate() {
	return birthdate;
    }

    /**
     * Sets the birthdate field value.
     * 
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
	this.birthdate = birthdate;
    }

    /**
     * Returns the address field value.
     * 
     * @return the address
     */
    public String getAddress() {
	return address;
    }

    /**
     * Sets the address field value.
     * 
     * @param address the address to set
     */
    public void setAddress(String address) {
	this.address = address;
    }

    /**
     * Returns the phone field value.
     * 
     * @return the phone
     */
    public String getPhone() {
	return phone;
    }

    /**
     * Sets the phone field value.
     * 
     * @param phone the phone to set
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
		    && (this.firstname.equals(otherUser.firstname))
		    && (this.lastname.equals(otherUser.lastname))
		    && (this.patronymic.equals(otherUser.patronymic))
		    && (this.birthdate.equals(otherUser.birthdate));
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
		+ ((firstname == null) ? 0 : firstname.hashCode())
		+ ((lastname == null) ? 0 : lastname.hashCode())
		+ ((patronymic == null) ? 0 : patronymic.hashCode())
		+ ((birthdate == null) ? 0 : birthdate.hashCode());
    }

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("user:").append(lastname).append(" ").append(firstname)
		.append(" ").append(patronymic).append(" birthdate:")
		.append(birthdate).append(" email:").append(email)
		.append(" address:").append(address).append(" phone:")
		.append(phone);
	return sb.toString();
    }
}
