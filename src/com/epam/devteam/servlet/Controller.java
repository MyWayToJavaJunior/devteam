package com.epam.devteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionFactory;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionDatabaseFailException;
import com.epam.devteam.action.exception.ActionException;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doAction(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doAction(request, response);
    }

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
	    handleException(request.getSession(), e);
	    result = new ActionResult(ActionResult.METHOD.REDIRECT, "error");
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

    private void handleException(HttpSession session, Exception e) {
	LOGGER.debug("Action execution failed.", e);
	LOGGER.error("Action execution failed.");
	if (e.getClass().equals(ActionBadRequestException.class)) {
	    session.setAttribute("error", "error.badRequest");
	} else if (e.getClass().equals(ActionDatabaseFailException.class)) {
	    session.setAttribute("error", "error.serverError");
	} else {
	    session.setAttribute("error", "error.serverError");
	}
    }
}
