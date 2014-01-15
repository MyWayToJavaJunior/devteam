package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>ShowEditAccountPageAction</code> returns edit-account.jsp page's
 * name and method to show page.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowEditAccountPageAction implements Action {

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
	return new ActionResult(ActionResult.METHOD.FORWARD, "edit-account");
    }
}
