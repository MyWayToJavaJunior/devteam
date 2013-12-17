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
public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ActionException {
		if ("login".equals(request.getParameter("command"))) {
			System.out.println(request.getParameter("username"));
			System.out.println(request.getParameter("password"));
			System.out.println("Welcome user!");
			return "home";
		}
		return "login";
	}

}
