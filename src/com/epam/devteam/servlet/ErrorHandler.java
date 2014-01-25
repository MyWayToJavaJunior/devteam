package com.epam.devteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionDatabaseFailException;

/**
 * The <code>ErrorHandler</code> is used to process exceptions.
 */
public class ErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	handleError(request, response);
	request.getRequestDispatcher("error").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	handleError(request, response);
	response.sendRedirect("error");
    }

    /**
     * Is used to process exception. It defines a type of exception and sets
     * required error message to session.
     * 
     * @param request The http request.
     * @param response The http response.
     * @throws ServletException If something fails.
     * @throws IOException If something fails.
     */
    private void handleError(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	Throwable exception = (Throwable) request
		.getAttribute("javax.servlet.error.exception");
	LOGGER.debug("Error Handler...");
	if (exception.getClass().equals(ActionBadRequestException.class)) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Bad request.");
	} else if (exception.getClass().equals(
		ActionDatabaseFailException.class)) {
	    session.setAttribute("error", "error.actionFailed ");
	    LOGGER.warn("Database fail.");
	} else {
	    session.setAttribute("error", "error.serverError");
	    LOGGER.warn("Server error.");
	}
	LOGGER.debug("Details:", exception);
    }

}
