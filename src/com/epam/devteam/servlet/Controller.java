package com.epam.devteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.action.ActionFactory;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
	super();
    }

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
	HttpSession session = request.getSession();
	Action action = ActionFactory.getAction(request);
	String view;
	try {
	    view = action.execute(request, response);
	} catch (ActionException e) {
	    LOGGER.error("Action execute failed.", e);
	    session.setAttribute("error", "action.failed");
	    view = "error";
	}
	if ("GET".equals(request.getMethod())) {
	    request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp")
		    .forward(request, response);
	} else {
	    response.sendRedirect(view);
	}

    }

}
