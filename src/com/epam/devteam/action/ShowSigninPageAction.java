/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Jan 5, 2014 	
 * @author Andrey Kovalskiy
 *
 */
public class ShowSigninPageAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	
	return "signin";
    }

}
