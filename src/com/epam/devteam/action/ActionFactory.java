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
    public static Action getAction(HttpServletRequest request) {
	Action action = null;
	String req = null;
	if (request != null) {
	    req = request.getMethod() + request.getPathInfo();
	    System.out.println(req);
	}
	if ("GET/signin".equals(req) || "POST/signin".equals(req)) {
	    action = new SigninAction();
	} else if ("GET/request".equals(req) || "POST/request_create".equals(req)) {
	    action = new RequestCreateAction();
	} else {
	    action = new MainAction();
	}
	return action;
    }
}
