package com.epam.devteam.entity.order;

import java.io.Serializable;
import java.sql.Date;

import com.epam.devteam.entity.user.Customer;

/**
 * The <code>Order</code> class contain all information about order.
 * 
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.entity.order.OrderStatus
 * @see com.epam.devteam.entity.order.OrderSubject
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
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
     * @param id The order id.
     * @param date The date of creation of the order.
     * @param status The order status.
     * @param subject The order subject.
     * @param topic The order topic.
     * @param message The order message.
     * @param fileName The name of the file attached to the order.
     * @param fileContent The content of the file attached to the order.
     * @param customer The customer who created the order.
     */
    public Order(int id, Date date, OrderStatus status, OrderSubject subject,
	    String topic, String message, String fileName, byte[] fileContent,
	    Customer customer) {
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

    /**
     * Returns hashcode of the object.
     */
    @Override
    public int hashCode() {
	return 31 * id + ((customer == null) ? 0 : customer.hashCode())
		+ ((date == null) ? 0 : date.hashCode())
		+ ((fileName == null) ? 0 : fileName.hashCode())
		+ ((message == null) ? 0 : message.hashCode())
		+ ((status == null) ? 0 : status.hashCode())
		+ ((subject == null) ? 0 : subject.hashCode())
		+ ((topic == null) ? 0 : topic.hashCode());
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
	    Order otherOrder = (Order) obj;
	    return (this.id == otherOrder.id)
		    && (this.date.equals(otherOrder.date))
		    && (this.status.equals(otherOrder.status))
		    && (this.subject.equals(otherOrder.subject))
		    && (this.topic.equals(otherOrder.topic))
		    && (this.message.equals(otherOrder.message))
		    && (this.fileName.equals(otherOrder.fileName))
		    && (this.fileContent.equals(otherOrder.fileContent))
		    && (this.customer.equals(otherOrder.customer));
	} else {
	    return false;
	}
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Order id:").append(id).append(", date:").append(date)
		.append(", status:").append(status).append(", subject:")
		.append(subject).append(", topic:").append(topic)
		.append(", message:").append(message).append(", fileName:")
		.append(fileName).append(", customer:")
		.append(customer.getFirstName()).append(" ")
		.append(customer.getLastName());
	return sb.toString();
    }

}
