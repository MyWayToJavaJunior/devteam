package com.epam.devteam.entity;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

public class Request implements Serializable {
    public enum Status {
	PENDING, IN_PROCESSING, RECEIVED, DENIED, UPDATED, TERMINATED
    }

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date date;
    private Status status;
    private File file;
    private Long customerId;
    private Long managerId;

    /**
     * Initializes a newly created {@code Request} object.
     */
    public Request() {
	super();
    }

    /**
     * Initializes a newly created {@code Request} object.
     * 
     * @param id The request id.
     * @param date The date of creation.
     * @param status Current status.
     * @param customer The customer who created the request.
     * @param file The file.
     * @param manager The manager who processes the request.
     */
    public Request(Long id, Date date, Status status, File file,
	    Long customerId, Long managerId) {
	super();
	this.id = id;
	this.date = date;
	this.status = status;
	this.file = file;
	this.customerId = customerId;
	this.managerId = managerId;
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
     * Returns the date field value.
     * 
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * Sets the date field value.
     * 
     * @param date the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * Returns the status field value.
     * 
     * @return the status
     */
    public Status getStatus() {
	return status;
    }

    /**
     * Sets the status field value.
     * 
     * @param status the status to set
     */
    public void setStatus(Status status) {
	this.status = status;
    }

    /**
     * Returns the file field value.
     * 
     * @return the file
     */
    public File getFile() {
	return file;
    }

    /**
     * Sets the file field value.
     * 
     * @param file the file to set
     */
    public void setFile(File file) {
	this.file = file;
    }

    /**
     * Returns the customerId field value.
     * 
     * @return the customerId
     */
    public Long getCustomerId() {
	return customerId;
    }

    /**
     * Sets the customerId field value.
     * 
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    /**
     * Returns the managerId field value.
     * 
     * @return the managerId
     */
    public Long getManagerId() {
	return managerId;
    }

    /**
     * Sets the managerId field value.
     * 
     * @param managerId the managerId to set
     */
    public void setManagerId(Long managerId) {
	this.managerId = managerId;
    }

}
