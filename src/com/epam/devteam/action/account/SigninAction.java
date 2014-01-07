/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
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
	HttpSession session = request.getSession();
	Customer customer;
	DaoFactory factory;
	CustomerDao dao;

	if (!isFormValuesValid(request, session)) {
	    session.setAttribute("signinError",
		    "Please enter email and password.");
	    LOGGER.debug("Sign in form fields are not valid.");
	    return "main";
	}
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
	    session.setAttribute("signinError",
		    "Account with such email and password is not found.");
	    LOGGER.debug("Account is not found.");
	} else {
	    session.setAttribute("user", customer);
	    if (session.getAttribute("signinError") != null) {
		session.removeAttribute("signinError");
	    }
	    LOGGER.debug("User " + customer.getEmail() + " has registered.");
	}
	return "main";

    }

    private boolean isFormValuesValid(HttpServletRequest request,
	    HttpSession session) {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	if (email.isEmpty()) {
	    return false;
	}
	if (password.isEmpty()) {
	    return false;
	}
	return true;
    }

}
