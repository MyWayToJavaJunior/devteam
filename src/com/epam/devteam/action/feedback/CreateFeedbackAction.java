package com.epam.devteam.action.feedback;

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
import com.epam.devteam.entity.feedback.Feedback;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.user.Employee;
import com.epam.devteam.service.FeedbackService;
import com.epam.devteam.service.ServiceException;
import com.epam.devteam.util.validator.FieldType;
import com.epam.devteam.util.validator.RequestFieldsValidator;
import com.epam.devteam.util.validator.ValidationException;

public class CreateFeedbackAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateFeedbackAction.class);
    private static final long fileMaxSize = 10485760L;
    private static final ServletFileUpload fileUpload = initServletFileUpload();
    private ActionResult result = new ActionResult();
    private FeedbackService feedbackCreator = new FeedbackService();

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	Employee manager;
	Feedback feedback;
	List<FileItem> items;
	InputStream stream;
	Order order;
	String tempOrderId = null;
	OrderStatus newOrderStatus = null;
	String message = null;
	String fieldName;
	String fieldValue;
	String fileName = null;
	int orderId = 0;
	byte[] fileContent = null;
	boolean formFieldsEqualNull = false;
	boolean fileNameLengthValid = false;
	boolean messageLengthValid = false;
	LOGGER.debug("Create feedback action...");
	session.removeAttribute("messageError");
	try {
	    items = fileUpload.parseRequest(request);
	} catch (FileUploadException e) {
	    LOGGER.debug("Excessive file size.");
	    throw new ActionBadRequestException(e);
	}
	for (FileItem item : items) {
	    if (item.isFormField()) {
		fieldName = item.getFieldName();
		fieldValue = item.getString();
		if ("order-status".equals(fieldName)) {
		    try {
			newOrderStatus = OrderStatus.valueOf(fieldValue);
		    } catch (IllegalArgumentException e) {
			LOGGER.warn("Unknown order status: " + fieldValue);
			throw new ActionBadRequestException(e);
		    }
		}
		if ("order-id".equals(fieldName)) {
		    tempOrderId = fieldValue;
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
	formFieldsEqualNull = RequestFieldsValidator.equalNull(tempOrderId,
		message);
	if (formFieldsEqualNull) {
	    LOGGER.warn("Form fields are not valid");
	    throw new ActionBadRequestException();
	}
	try {
	    orderId = Integer.parseInt(tempOrderId);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Id field value is not valid.");
	    throw new ActionBadRequestException(e);
	}
	order = (Order) session.getAttribute("order");
	if ((order == null) || (order.getId() != orderId)) {
	    LOGGER.warn("Feedback cannot be created for required order.");
	    throw new ActionBadRequestException();
	}
	session.setAttribute("message", message);
	if (RequestFieldsValidator.empty(message)) {
	    session.setAttribute("messageError", "order.messageEmpty");
	    result.setMethod(ActionResult.METHOD.REDIRECT);
	    result.setView(request.getHeader("referer"));
	    return result;
	}
	try {
	    fileNameLengthValid = RequestFieldsValidator.lengthValid(
		    FieldType.INPUT_TEXT, fileName);
	} catch (ValidationException e) {
	    LOGGER.warn("File name cannot be validated.");
	    throw new ActionException(e);
	}
	if (!fileNameLengthValid) {
	    LOGGER.warn("File name value is not valid.");
	    throw new ActionBadRequestException();
	}
	try {
	    messageLengthValid = RequestFieldsValidator.lengthValid(
		    FieldType.TEXTAREA, message);
	} catch (ValidationException e) {
	    LOGGER.warn("Message field cannot be validated.");
	    throw new ActionException(e);
	}
	if (!messageLengthValid) {
	    LOGGER.warn("Message field value is not valid.");
	    throw new ActionBadRequestException();
	}
	manager = (Employee) session.getAttribute("user");
	feedback = new Feedback();
	feedback.setOrderId(order.getId());
	feedback.setDate(new Date(new java.util.Date().getTime()));
	feedback.setMessage(message);
	feedback.setFileName(fileName);
	feedback.setFileContent(fileContent);
	feedback.setManager(manager);
	order.setStatus(newOrderStatus);
	try {
	    feedbackCreator.createFeedback(feedback, order);
	    LOGGER.debug("Order has been created.");
	} catch (ServiceException e) {
	    LOGGER.warn("Feedback cannot be created.");
	    throw new ActionException(e);
	}
	session.setAttribute("success", "order.createSuccess");
	session.removeAttribute("message");
	session.removeAttribute("order");
	result.setMethod(ActionResult.METHOD.REDIRECT);
	result.setView("success");
	return result;
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

}
