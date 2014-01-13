package com.epam.devteam.entity.response;

import java.io.Serializable;
import java.sql.Date;

import com.epam.devteam.entity.user.Employee;

public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Date date;
    private int orderId;
    private Employee manager;
    private String message;
    private String fileName;
    private byte[] fileContent;

    /**
     * Initializes a newly created {@code Feedback} object.
     */
    public Feedback() {
	super();
    }

    /**
     * Initializes a newly created {@code Feedback} object.
     * 
     * @param id
     * @param date
     * @param orderId
     * @param manager
     * @param message
     * @param fileName
     * @param fileContent
     */
    public Feedback(int id, Date date, int orderId, Employee manager,
	    String message, String fileName, byte[] fileContent) {
	super();
	this.id = id;
	this.date = date;
	this.orderId = orderId;
	this.manager = manager;
	this.message = message;
	this.fileName = fileName;
	this.fileContent = fileContent;
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
     * Returns the date field value.
     * 
     * @return The date.
     */
    public Date getDate() {
	return date;
    }

    /**
     * Sets the date field value.
     * 
     * @param date The date to set.
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * Returns the orderId field value.
     * 
     * @return The orderId.
     */
    public int getOrderId() {
	return orderId;
    }

    /**
     * Sets the orderId field value.
     * 
     * @param orderId The orderId to set.
     */
    public void setOrderId(int orderId) {
	this.orderId = orderId;
    }

    /**
     * Returns the manager field value.
     * 
     * @return The manager.
     */
    public Employee getManager() {
	return manager;
    }

    /**
     * Sets the manager field value.
     * 
     * @param manager The manager to set.
     */
    public void setManager(Employee manager) {
	this.manager = manager;
    }

    /**
     * Returns the message field value.
     * 
     * @return The message.
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets the message field value.
     * 
     * @param message The message to set.
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Returns the fileName field value.
     * 
     * @return The fileName.
     */
    public String getFileName() {
	return fileName;
    }

    /**
     * Sets the fileName field value.
     * 
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    /**
     * Returns the fileContent field value.
     * 
     * @return The fileContent.
     */
    public byte[] getFileContent() {
	return fileContent;
    }

    /**
     * Sets the fileContent field value.
     * 
     * @param fileContent The fileContent to set.
     */
    public void setFileContent(byte[] fileContent) {
	this.fileContent = fileContent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result
		+ ((fileName == null) ? 0 : fileName.hashCode());
	result = prime * result + id;
	result = prime * result + ((manager == null) ? 0 : manager.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + orderId;
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Feedback other = (Feedback) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (fileName == null) {
	    if (other.fileName != null)
		return false;
	} else if (!fileName.equals(other.fileName))
	    return false;
	if (id != other.id)
	    return false;
	if (manager == null) {
	    if (other.manager != null)
		return false;
	} else if (!manager.equals(other.manager))
	    return false;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	if (orderId != other.orderId)
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Feedback [id=" + id + ", date=" + date + ", orderId=" + orderId
		+ ", manager=" + manager + ", message=" + message
		+ ", fileName=" + fileName + "]";
    }

}
