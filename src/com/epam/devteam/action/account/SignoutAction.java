/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;

/**
 * @date Jan 6, 2014 	
 * @author Andrey Kovalskiy
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
