package com.epam.devteam.action.order;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.order.OrderSubject;
import com.epam.devteam.entity.user.Customer;

public class CreateOrderAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateOrderAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = null;
	DaoFactory factory = null;
	OrderDao dao = null;
	Customer customer = null;
	Order order = null;
	OrderSubject subject = null;
	String topic = null;
	String message = null;
	String fileName = null;
	byte[] fileContent = null;

	try {
	    List<FileItem> items = new ServletFileUpload(
		    new DiskFileItemFactory()).parseRequest(request);
	    String fieldName;
	    String fieldValue;
	    InputStream stream;
	    for (FileItem item : items) {
		if (item.isFormField()) {
		    fieldName = item.getFieldName();
		    fieldValue = item.getString();
		    if ("subject".equals(fieldName)) {
			try {
			    subject = OrderSubject.valueOf(fieldValue);
			} catch (IllegalArgumentException e) {
			    LOGGER.warn("Unknown order subject: " + fieldValue);
			    throw new ActionException();
			}
		    }
		    if ("topic".equals(fieldName)) {
			topic = fieldValue;
		    }
		    if ("message".equals(fieldName)) {
			message = fieldValue;
		    }
		} else {
		    fieldName = item.getFieldName();
		    if ("file".equals(fieldName)) {
			fileName = FilenameUtils.getName(item.getName());
			stream = item.getInputStream();
			fileContent = IOUtils.toByteArray(stream);
			System.out.println(fileName);
		    }
		}
	    }
	} catch (Exception e) {
	    LOGGER.warn("Multipart request cannot be parsed.", e);
	    throw new ActionException();
	}
	if (!isFormValuesValid(session, topic, message)) {
	    LOGGER.debug("Action failed: form fields are not valid");
	    return request.getHeader("referer");
	}
	session = request.getSession();
	customer = (Customer) session.getAttribute("user");
	order = new Order();
	order.setDate(new Date(new java.util.Date().getTime()));
	order.setStatus(OrderStatus.PENDING);
	order.setSubject(subject);
	order.setTopic(topic);
	order.setMessage(message);
	order.setFileName(fileName);
	order.setFileContent(fileContent);
	order.setCustomer(customer);
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	dao = factory.getOrderDao();
	try {
	    dao.create(order);
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be created.");
	    throw new ActionException();
	}
	LOGGER.debug("Order has been created.");
	session.setAttribute("success", "order.create.success");
	return "success";
    }

    private boolean isFormValuesValid(HttpSession session, String topic,
	    String message) throws ActionException {
	if (message == null || topic == null) {
	    LOGGER.warn("Form field error was found.");
	    return false;
	}
	if (topic.isEmpty()) {
	    session.setAttribute("error", "order.create.topicEmpty");
	    return false;
	}
	if (message.isEmpty()) {
	    session.setAttribute("error", "order.create.messageEmpty");
	    return false;
	}
	return true;
    }
}
