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

import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.entity.feedback.Feedback;
import com.epam.devteam.entity.order.Order;

/**
 * The <code>DownloadFileAction</code> is used to download files from order or
 * feedback.
 * 
 * @date Jan 19, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class DownloadFileAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(DownloadFileAction.class);
    private static final int DEFAULT_BUFFER_SIZE = 10240;
    private ActionResult result = new ActionResult();

    /**
     * Is used to perform required actions and define method and view for
     * <code>Controller</code>. Returns result as <code>ActionResult</code>.
     * 
     * @param request Request to process.
     * @param response Response to send.
     * @return ActionResult where to redirect user
     * @throws ActionException If something fails during method performing.
     */
    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	Order order;
	Feedback feedback;
	String source = request.getParameter("source");
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	String fileName;
	byte[] fileContent;
	LOGGER.debug("Download file action...");
	if ("order".equals(source)) {
	    order = (Order) session.getAttribute("order");
	    fileName = order.getFileName();
	    fileContent = order.getFileContent();
	} else if ("feedback".equals(source)) {
	    feedback = (Feedback) session.getAttribute("feedback");
	    fileName = feedback.getFileName();
	    fileContent = feedback.getFileContent();
	} else {
	    LOGGER.warn("Source not defined.");
	    throw new ActionBadRequestException();
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
	    LOGGER.debug("File cannot be downloaded.");
	    throw new ActionException();
	} finally {
	    close(output);
	    close(input);
	}
	result.setMethod(ActionResult.METHOD.REDIRECT);
	result.setView(request.getHeader("referer"));
	return result;
    }

    /**
     * Is used to close input and output streams.
     * 
     * @param resource The resource to close.
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
