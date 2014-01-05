package com.epam.devteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
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
	try {
	    Action action = ActionFactory.getAction(request);
	    String view = action.execute(request, response);
	    if (view.equals(request.getPathInfo().substring(1))) {
		request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp")
			.forward(request, response);
	    } else {
		response.sendRedirect(view);
	    }
	} catch (Exception e) {
	    LOGGER.error("Executing action failed.", e);
	}
    }

}
