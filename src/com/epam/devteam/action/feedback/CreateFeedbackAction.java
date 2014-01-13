package com.epam.devteam.action.feedback;

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
import com.epam.devteam.action.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.FeedbackDao;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.response.Feedback;
import com.epam.devteam.entity.user.Employee;

public class CreateFeedbackAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateFeedbackAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session;
	DaoFactory factory;
	FeedbackDao feedbackDao;
	OrderDao orderDao;
	Feedback feedback;
	Employee manager;
	int orderId = 0;
	OrderStatus status = null;
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
		    if ("order-id".equals(fieldName)) {
			System.out.println(fieldValue);
			try {
			    orderId = Integer.parseInt(fieldValue);
			} catch (IllegalArgumentException e) {
			    LOGGER.warn("Order id is not defined or not correct.");
			    throw new ActionException();
			}
		    }
		    if ("status".equals(fieldName)) {
			try {
			    status = OrderStatus.valueOf(fieldValue);
			} catch (IllegalArgumentException e) {
			    LOGGER.warn("Unknown order status: " + fieldValue);
			    throw new ActionException();
			}
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
	session = request.getSession();
	if (!isFormValuesValid(session, message)) {
	    LOGGER.debug("Action failed: form fields are not valid");
	    return request.getHeader("referer");
	}
	manager = (Employee) session.getAttribute("user");
	feedback = new Feedback();
	feedback.setDate(new Date(new java.util.Date().getTime()));
	feedback.setOrderId(orderId);
	feedback.setMessage(message);
	feedback.setFileName(fileName);
	feedback.setFileContent(fileContent);
	feedback.setManager(manager);
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	feedbackDao = factory.getFeedbackDao();
	try {
	    feedbackDao.create(feedback);
	} catch (DaoException e) {
	    LOGGER.warn("Feedback cannot be created.");
	    throw new ActionException();
	}
	orderDao = factory.getOrderDao();
	try {
	    orderDao.updateStatus(orderId, status);
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be updated.");
	    throw new ActionException();
	}
	LOGGER.debug("Order has been created.");
	session.setAttribute("success", "feedback.create.success");
	return "success";
    }

    private boolean isFormValuesValid(HttpSession session, String message)
	    throws ActionException {
	if (message == null) {
	    LOGGER.warn("Form field error was found.");
	    return false;
	}
	if (message.isEmpty()) {
	    session.setAttribute("error", "order.create.messageEmpty");
	    return false;
	}
	return true;
    }
}
