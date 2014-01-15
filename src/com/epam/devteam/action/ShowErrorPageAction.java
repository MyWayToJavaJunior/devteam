/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.exception.ActionException;

/**
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowErrorPageAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	return new ActionResult(ActionResult.METHOD.FORWARD, "error");
    }

}
