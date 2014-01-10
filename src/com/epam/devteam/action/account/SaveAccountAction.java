package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.Customer;
import com.epam.devteam.entity.Employee;
import com.epam.devteam.entity.User;
import com.epam.devteam.entity.UserRole;

/**
 * @date Jan 8, 2014
 * @author Andrey Kovalskiy
 */
public class SaveAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(SaveAccountAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	UserRole role;
	try {
	    role = UserRole.valueOf(request.getParameter("role"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role was detected: "
		    + session.getAttribute("role"));
	    throw new ActionException();
	}
	User account;
	switch (role) {
	case CUSTOMER:
	    Customer customer = new Customer();
	    customer.setCompany(request.getParameter("company"));
	    customer.setPosition(request.getParameter("position"));
	    account = customer;
	    break;
	default:
	    Employee employee = new Employee();
	    employee.setQualification(request.getParameter("qualification"));
	    account = employee;
	    break;
	}
	Integer accountId;
	try {
	    accountId = Integer.parseInt(request.getParameter("id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Illegal id is defined");
	    throw new ActionException();
	}
	account.setId(accountId);
	account.setRole(role);
	account.setActive(Boolean.parseBoolean(request
		.getParameter("is-active")));
	account.setFirstName(request.getParameter("first-name"));
	account.setLastName(request.getParameter("last-name"));
	account.setBirthDate(null);
	account.setAddress(request.getParameter("address"));
	account.setPhone(request.getParameter("phone"));
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taken.");
	    throw new ActionException();
	}
	UserDao dao = factory.getUserDao();
	try {
	    dao.update(account);
	} catch (DaoException e) {
	    LOGGER.warn("Customer cannot be updated.");
	    throw new ActionException();
	}
	User user = (User) session.getAttribute("user");
	if (user.getId().equals(account.getId())) {
	    session.setAttribute("user", account);
	} else {
	    session.removeAttribute("accountToManage");
	}
	session.setAttribute("success",
		"Account has been modified successfully.");
	LOGGER.debug("User " + account + " has been updated.");
	return "success";
    }

}
