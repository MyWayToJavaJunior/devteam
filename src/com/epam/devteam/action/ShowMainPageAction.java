/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * The <code>ShowMainPageAction</code> returns main.jsp page's name and method
 * to show it.
 * 
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowMainPageAction implements Action {
    private final static Logger LOGGER = Logger
	    .getLogger(ShowMainPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	LOGGER.debug("Action starts.");
	System.out.println(request.getSession().getAttribute("user"));
	return new ActionResult(ActionResult.METHOD.FORWARD, "main");
    }
}
