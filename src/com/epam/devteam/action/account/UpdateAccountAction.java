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
 * @date Jan 8, 2014
 * @author Andrey Kovalskiy
 */
public class UpdateAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(UpdateAccountAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	Customer customer = (Customer) session.getAttribute("user");
	customer.setFirstName(request.getParameter("first-name"));
	customer.setLastName(request.getParameter("last-name"));
	customer.setBirthDate(null);
	customer.setAddress(request.getParameter("address"));
	customer.setPhone(request.getParameter("phone"));
	customer.setCompany(request.getParameter("company"));
	customer.setPosition(request.getParameter("position"));
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taken.");
	    throw new ActionException();
	}
	CustomerDao dao = factory.getCustomerDao();
	try {
	    dao.update(customer);
	} catch (DaoException e) {
	    LOGGER.warn("Customer cannot be updated.");
	    throw new ActionException();
	}
	session.setAttribute("user", customer);
	session.setAttribute("message", "Your account has been modified successfully.");
	if (session.getAttribute("accountError") != null) {
	    session.removeAttribute("accountError");
	}
	LOGGER.debug("User " + customer.getEmail() + " has been updated.");
	return "account";
    }

}
