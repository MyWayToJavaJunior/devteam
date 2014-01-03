/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Jan 2, 2014 	
 * @author anjey
 *
 */
public class RequestCreateAction implements Action{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	// TODO Auto-generated method stub
	return "request";
    }
    
}
