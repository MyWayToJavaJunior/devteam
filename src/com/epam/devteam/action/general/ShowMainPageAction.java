/**
 * 
 */
package com.epam.devteam.action.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

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
	LOGGER.debug("Action starts.");
	return new ActionResult(ActionResult.METHOD.FORWARD, "main");
    }
}
