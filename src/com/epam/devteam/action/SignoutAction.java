/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Jan 6, 2014 	
 * @author anjey
 *
 */
public class SignoutAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	request.getSession().removeAttribute("user");
	return "main";
    }

}
