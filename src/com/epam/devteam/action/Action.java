/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>Action</code> interface provides a method for http request
 * processing.
 * 
 * @date Dec 14, 2014
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.action.ActionResult
 */
public interface Action {

    /**
     * Is used to perform required actions and define method and view for
     * <code>Controller</code>. Returns result as <code>ActionResult</code>.
     * 
     * @param request Request to process.
     * @param response Response to send.
     * @return ActionResult where to redirect user
     * @throws ActionException If something fails during method performing.
     */
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException;

}
