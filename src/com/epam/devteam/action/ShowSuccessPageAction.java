/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Jan 10, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowSuccessPageAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	return "success";
    }

}
