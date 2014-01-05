/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Dec 14, 2013
 * @author Andrey Kovalskiy
 * 
 */
public interface Action {

    /**
     * Is used to make required action and get view where to redirect user.
     * 
     * @param request Request to process.
     * @param response Response to send.
     * @return View where to redirect user
     * @throws ActionException
     */
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException;
}
