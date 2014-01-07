/**
 * 
 */
package com.epam.devteam.action.account;

import java.sql.Date;

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
import com.epam.devteam.entity.UserRole;

/**
 * @date Jan 4, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class CreateAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateAccountAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	if (!isFormValuesValid(request, session)) {
	    return "account";
	}
	Customer customer = new Customer();
	customer.setEmail(request.getParameter("email"));
	customer.setPassword(request.getParameter("password"));
	customer.setRegistrationDate(new Date(new java.util.Date().getTime()));
	customer.setRole(UserRole.CUSTOMER);
	customer.setActive(true);
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	CustomerDao dao = factory.getCustomerDao();
	try {
	    dao.create(customer);
	} catch (DaoException e) {
	    LOGGER.warn("Customer cannot be created.");
	    throw new ActionException();
	}
	session.setAttribute("user", customer);
	if (session.getAttribute("accountError") != null) {
	    session.removeAttribute("accountError");
	}
	LOGGER.debug("User " + customer.getEmail() + " has been created.");
	return "account";
    }

    private boolean isFormValuesValid(HttpServletRequest request,
	    HttpSession session) {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String passwordConfirm = request.getParameter("password-confirm");

	if (email.isEmpty()) {
	    session.setAttribute("accountError", "Please enter email.");
	    return false;
	}
	if (password.isEmpty()) {
	    session.setAttribute("accountError", "Please enter password.");
	    return false;
	}
	if (!password.equals(passwordConfirm)) {
	    session.setAttribute("accountError", "Passwords are not equal.");
	    return false;
	}
	return true;
    }

}
