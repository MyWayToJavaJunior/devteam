/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;

/**
 * @date Dec 14, 2013 	
 * @author anjey
 *
 */
public class ActionFactory {
	public static Action getAction(HttpServletRequest request){
		Action action = null;
		String req = null;
		if (request != null) {
			req = request.getMethod() + request.getPathInfo();
			System.out.println(req);
		}
		if ("GET/login".equals(req) || "POST/login".equals(req)) {
			action = new LoginAction();
		} else if ("GET/home".equals(req)) {
			action = new HomeAction();
		} else {
			action = new MainAction();
		}
		return action;  
	}
}
