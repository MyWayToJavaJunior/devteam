/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.RequestDao;
import com.epam.devteam.entity.Request;

/**
 * @date Jan 2, 2014
 * @author anjey
 * 
 */
public class RequestCreateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RequestCreateAction.class);
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	if ("request_create".equals(request.getParameter("command"))) {
	    try {
		DaoFactory df = DaoFactory.takeDaoFactory();
		RequestDao rd = df.getRequestDao();
		Request onlineRequest = new Request();
		
	    } catch (DaoException e) {
		LOGGER.warn("Failed.");
	    }
	    
	}
	return "request";
    }

}
