/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;

/**
 * @date Jan 7, 2014 	
 * @author anjey
 *
 */
public class ShowAccountPageAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {

	return "account";
    }

}
