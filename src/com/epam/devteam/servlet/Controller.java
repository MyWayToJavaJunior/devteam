package com.epam.devteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionFactory;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * Is used to handle all the requests that are addressed to this web site.
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;

    /**
     * Is used to process GET request.
     * 
     * @param request Http request.
     * @param response Http response.
     * @throws ServletException If the target resource throws this exception
     * @throws IOException If the target resource throws this exception
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doAction(request, response);
    }

    /**
     * Is used to process POST request.
     * 
     * @param request Http request.
     * @param response Http response.
     * @throws ServletException If the target resource throws this exception
     * @throws IOException If the target resource throws this exception
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doAction(request, response);
    }

    /**
     * Is used to process current request, perform any action and show jsp page
     * with result.
     * 
     * @param request Http request
     * @param response Http response
     * @throws ServletException If the target resource throws this exception
     * @throws IOException If the target resource throws this exception
     */
    protected void doAction(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	Action action;
	ActionResult result;
	ActionResult.METHOD method;
	String view;
	action = ActionFactory.getAction(request);
	try { 
	    result = action.execute(request, response);
	} catch (ActionException e) {
	    throw new ServletException(e);
	}
	method = result.getMethod();
	view = result.getView();
	LOGGER.debug(method + "/" + view);
	switch (method) {
	case FORWARD:
	    request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp")
		    .forward(request, response);
	    break;
	case REDIRECT:
	    response.sendRedirect(view);
	    break;
	}

    }

}