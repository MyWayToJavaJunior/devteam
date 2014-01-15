package com.epam.devteam.action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.exception.ActionException;

/**
 * @date Jan 11, 2014
 * @author Andrey Kovalskiy
 */
public class ShowCreateOrderPageAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	return "create-order";
    }

}
