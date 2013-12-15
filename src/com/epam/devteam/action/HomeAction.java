/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date Dec 14, 2013 	
 * @author anjey
 *
 */
public class HomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ActionException {
		return "home";
	}
	
	
	
}
