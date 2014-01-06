/**
 * 
 */
package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.dao.CustomerDao;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.entity.Customer;

/**
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class SigninAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(SigninAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	Customer customer = null;
	HttpSession session = request.getSession();
	DaoFactory factory;
	CustomerDao dao = null;
	try {
	    factory = DaoFactory.getDaoFactory();
	    dao = factory.getCustomerDao();
	} catch (DaoException e) {
	    LOGGER.warn("Dao cannot be created.");
	    throw new ActionException();
	}
	try {
	    customer = dao.find(request.getParameter("email"),
		    request.getParameter("password"));
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionException();
	}
	if (customer == null) {
	    System.out.println("User not found.");
	    return "signin";
	} else {
	    session.setAttribute("user", customer);
	    System.out.println("welcome: " + customer.getEmail());
	    return "main";
	}

    }

}
