package com.epam.devteam.entity;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;
    private String position;
    private String company;

    /**
     * Returns the position field value.
     * 
     * @return the position
     */
    public String getPosition() {
	return position;
    }

    /**
     * Sets the position field value.
     * 
     * @param position the position to set
     */
    public void setPosition(String position) {
	this.position = position;
    }

    /**
     * Returns the company field value.
     * 
     * @return the company
     */
    public String getCompany() {
	return company;
    }

    /**
     * Sets the company field value.
     * 
     * @param company the company to set
     */
    public void setCompany(String company) {
	this.company = company;
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
	    return (this.getId().equals(otherEmployee.getId()))
		    && (this.getEmail().equals(otherEmployee.getEmail()))
		    && (this.getPassword().equals(otherEmployee.getPassword()))
		    && (this.getFirstname()
			    .equals(otherEmployee.getFirstname()))
		    && (this.getLastname().equals(otherEmployee.getLastname()))
		    && (this.getPatronymic().equals(otherEmployee
			    .getPatronymic()))
		    && (this.getBirthdate()
			    .equals(otherEmployee.getBirthdate()))
		    && (this.company.equals(otherEmployee.company))
		    && (this.position.equals(otherEmployee.position));
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
		+ ((position == null) ? 0 : position.hashCode())
		+ ((company == null) ? 0 : company.hashCode());
    }

}
