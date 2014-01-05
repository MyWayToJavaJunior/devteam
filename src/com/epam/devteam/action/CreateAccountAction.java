/**
 * 
 */
package com.epam.devteam.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
public class CreateAccountAction implements Action{
    private static final Logger LOGGER = Logger.getLogger(CreateAccountAction.class);
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	Customer customer = new Customer();
	customer.setEmail(request.getParameter("email"));
	customer.setPassword(request.getParameter("password"));
	customer.setRegistrationDate(new Date(new java.util.Date().getTime()));
	customer.setRole(UserRole.CUSTOMER);
	customer.setFirstName(request.getParameter("first_name"));
	customer.setLastName(request.getParameter("last_name"));
	customer.setBirthDate(null);
	customer.setAddress(request.getParameter("address"));
	customer.setPhone(request.getParameter("phone"));
	customer.setCompany(request.getParameter("company"));
	customer.setPosition(request.getParameter("position"));
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be geted.");
	    throw new ActionException();
	}
	CustomerDao dao = factory.getCustomerDao();
	try {
	    dao.create(customer);
	} catch (DaoException e) {
	    LOGGER.warn("Customer cannot be created.");
	    throw new ActionException();
	}
	return "main";
    }

}
