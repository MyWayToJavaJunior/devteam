package com.epam.devteam.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.response.Feedback;

public class DownloadFileAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(DownloadFileAction.class);
    private static final int DEFAULT_BUFFER_SIZE = 10240;

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session;
	Order order;
	Feedback feedback;
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	String fileName;
	byte[] fileContent;

	session = request.getSession();
	String source = request.getParameter("source");
	if (source == null
		|| (!"order".equals(source) && !"feedback".equals(source))) {
	    LOGGER.warn("Source not defined.");
	    throw new ActionException();
	}
	if ("order".equals(source)) {
	    order = (Order) session.getAttribute("order");
	    fileName = order.getFileName();
	    fileContent = order.getFileContent();
	} else {
	    feedback = (Feedback) session.getAttribute("feedback");
	    fileName = feedback.getFileName();
	    fileContent = feedback.getFileContent();
	}
	response.reset();
	response.setBufferSize(DEFAULT_BUFFER_SIZE);
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Length", fileName);
	response.setHeader("Content-Disposition", "attachment; filename=\""
		+ fileName + "\"");
	try {
	    input = new BufferedInputStream(new ByteArrayInputStream(
		    fileContent), DEFAULT_BUFFER_SIZE);
	    output = new BufferedOutputStream(response.getOutputStream(),
		    DEFAULT_BUFFER_SIZE);
	    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	    int length;
	    while ((length = input.read(buffer)) > 0) {
		output.write(buffer, 0, length);
	    }
	} catch (IOException e) {
	    LOGGER.debug("File download failed.");
	    throw new ActionException();
	} finally {
	    close(output);
	    close(input);
	}
	return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader("referer"));
    }
    
    /**
     * 
     * Is used to ...
     * @param resource
     */
    private void close(Closeable resource) {
	if (resource != null) {
	    try {
		resource.close();
	    } catch (IOException e) {
		LOGGER.warn("Resource cannot be closed.");
	    }
	}
    }
}
