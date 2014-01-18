package com.epam.devteam.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>ShowCreateOrderPageAction</code> class is used to show page where
 * customer can create a new order.
 * 
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public class ShowCreateOrderPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowCreateOrderPageAction.class);
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
	LOGGER.debug("Show create order page...");
	result.setMethod(ActionResult.METHOD.FORWARD);
	result.setView("create-order");
	return result;
    }

}
