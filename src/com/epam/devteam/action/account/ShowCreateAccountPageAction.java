/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>ShowCreateAccountPageAction</code> returns create-account.jsp
 * pages's name and method to show page.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowCreateAccountPageAction implements Action {
    private final static Logger LOGGER = Logger
	    .getLogger(ShowCreateAccountPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	LOGGER.debug("Action starts.");
	return new ActionResult(ActionResult.METHOD.FORWARD, "create-account");
    }

}
