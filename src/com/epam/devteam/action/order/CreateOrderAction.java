package com.epam.devteam.action.order;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.order.OrderSubject;
import com.epam.devteam.entity.user.Customer;
import com.epam.devteam.service.validation.RequestFieldsValidator;

public class CreateOrderAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateOrderAction.class);
    private static final long fileMaxSize = 10485760L;
    private static final ServletFileUpload fileUpload = initServletFileUpload();
    private DaoFactory factory;
    private OrderDao dao;

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session;
	Customer customer;
	Order order;
	List<FileItem> items;
	InputStream stream;
	OrderSubject subject = null;
	String topic = null;
	String message = null;
	String fieldName;
	String fieldValue;
	String fileName = null;
	byte[] fileContent = null;
	boolean formFieldsEqualNull = false;
	LOGGER.debug("Create order action...");
	try {
	    items = fileUpload.parseRequest(request);
	} catch (FileUploadException e) {
	    LOGGER.warn("Request cannot be parsed.");
	    throw new ActionBadRequestException(e);
	}
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
		    try {
			stream = item.getInputStream();
			fileContent = IOUtils.toByteArray(stream);
		    } catch (IOException e) {
			LOGGER.warn("Input stream error.");
			throw new ActionException(e);
		    }
		    LOGGER.debug("File " + fileName + " has been uploaded.");
		}
	    }
	}
	formFieldsEqualNull = RequestFieldsValidator.equalNull(topic, message);
	if (formFieldsEqualNull) {
	    LOGGER.warn("Form fields are not valid");
	    throw new ActionBadRequestException();
	}
	session = request.getSession();
	session.setAttribute("topic", topic);
	session.setAttribute("message", message);
	session.removeAttribute("topicError");
	session.removeAttribute("messageError");
	if (RequestFieldsValidator.empty(topic)) {
	    session.setAttribute("topicError", "order.topicEmpty");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	if (RequestFieldsValidator.empty(message)) {
	    session.setAttribute("messageError", "order.messageEmpty");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
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
	    orderDao().create(order);
	    LOGGER.debug("Order has been created.");
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be created.");
	    throw new ActionException();
	}
	session.setAttribute("success", "order.create.success");
	session.removeAttribute("topic");
	session.removeAttribute("message");
	return new ActionResult(ActionResult.METHOD.REDIRECT, "success");
    }

    /**
     * Is used to initialize files handler and set maximum size of uploaded
     * files as 10Mb.
     * 
     * @return The files handler.
     */
    private static ServletFileUpload initServletFileUpload() {
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload fileUpload = new ServletFileUpload(factory);
	fileUpload.setFileSizeMax(fileMaxSize);
	return fileUpload;
    }

    /**
     * Is used to get dao factory. It initializes factory during the first use.
     * 
     * @return Dao factory.
     * @throws DaoException If something fails.
     */
    private DaoFactory daoFactory() throws DaoException {
	if (factory == null) {
	    factory = DaoFactory.getDaoFactory();
	}
	return factory;
    }

    /**
     * Is used to get order dao. It initializes dao during the first use.
     * 
     * @return The order dao.
     * @throws DaoException If something fails.
     */
    private OrderDao orderDao() throws DaoException {
	if (dao == null) {
	    dao = daoFactory().getOrderDao();
	}
	return dao;
    }
}
