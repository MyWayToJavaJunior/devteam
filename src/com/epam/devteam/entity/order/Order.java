package com.epam.devteam.entity.order;

import java.io.Serializable;
import java.sql.Date;

import com.epam.devteam.entity.Customer;

/**
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date date;
    private OrderStatus status;
    private OrderSubject subject;
    private String topic;
    private String message;
    private String fileName;
    private byte[] fileContent;
    private Customer customer;

    /**
     * Initializes a newly created {@code Order} object.
     */
    public Order() {
	super();
    }

    /**
     * Initializes a newly created {@code Object} object.
     * 
     * @param id
     * @param date
     * @param status
     * @param subject
     * @param topic
     * @param message
     * @param fileName
     * @param fileContent
     * @param customer
     */
    public Order(Long id, Date date, OrderStatus status, OrderSubject subject,
	    String topic, String message, String fileName,
	    byte[] fileContent, Customer customer) {
	super();
	this.id = id;
	this.date = date;
	this.status = status;
	this.subject = subject;
	this.topic = topic;
	this.message = message;
	this.fileName = fileName;
	this.fileContent = fileContent;
	this.customer = customer;
    }

    /**
     * Returns the id field value.
     * 
     * @return The id.
     */
    public Long getId() {
	return id;
    }

    /**
     * Sets the id field value.
     * 
     * @param id The id to set.
     */
    public void setId(Long id) {
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
     * Returns the status field value.
     * 
     * @return The status.
     */
    public OrderStatus getStatus() {
	return status;
    }

    /**
     * Sets the status field value.
     * 
     * @param status The status to set.
     */
    public void setStatus(OrderStatus status) {
	this.status = status;
    }

    /**
     * Returns the subject field value.
     * 
     * @return The subject.
     */
    public OrderSubject getSubject() {
	return subject;
    }

    /**
     * Sets the subject field value.
     * 
     * @param subject The subject to set.
     */
    public void setSubject(OrderSubject subject) {
	this.subject = subject;
    }

    /**
     * Returns the topic field value.
     * 
     * @return The topic.
     */
    public String getTopic() {
	return topic;
    }

    /**
     * Sets the topic field value.
     * 
     * @param topic The topic to set.
     */
    public void setTopic(String topic) {
	this.topic = topic;
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

    /**
     * Returns the customer field value.
     * 
     * @return The customer.
     */
    public Customer getCustomer() {
	return customer;
    }

    /**
     * Sets the customer field value.
     * 
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) {
	this.customer = customer;
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
	result = prime * result
		+ ((customer == null) ? 0 : customer.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result
		+ ((fileName == null) ? 0 : fileName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((subject == null) ? 0 : subject.hashCode());
	result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
	Order other = (Order) obj;
	if (customer == null) {
	    if (other.customer != null)
		return false;
	} else if (!customer.equals(other.customer))
	    return false;
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
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	if (status != other.status)
	    return false;
	if (subject != other.subject)
	    return false;
	if (topic == null) {
	    if (other.topic != null)
		return false;
	} else if (!topic.equals(other.topic))
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
	return "Order [id=" + id + ", date=" + date + ", status=" + status
		+ ", subject=" + subject + ", topic=" + topic + ", message="
		+ message + ", fileName=" + fileName + ", customer=" + customer
		+ "]";
    }

}
