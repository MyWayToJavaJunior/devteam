/**
 * 
 */
package com.epam.devteam.action.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * @date Jan 10, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowSuccessPageAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	return new ActionResult(ActionResult.METHOD.FORWARD, "success");
    }

}
