package com.epam.devteam.action.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>ShowContactsPageAction</code> is used to show page with contacts.
 * 
 * @date Jan 18, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowContactsPageAction implements Action {
    private final static Logger LOGGER = Logger
	    .getLogger(ShowContactsPageAction.class);

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
	LOGGER.debug("Show contacts page action...");
	return new ActionResult(ActionResult.METHOD.FORWARD, "contacts");
    }
}
