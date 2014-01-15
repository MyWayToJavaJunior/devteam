package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.action.ActionResult;

/**
 * The <code>ShowEditAccountPageAction</code> returns edit-account.jsp page's
 * name and method to show page.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowEditAccountPageAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	return new ActionResult(ActionResult.METHOD.GET, "edit-account");
    }
}
