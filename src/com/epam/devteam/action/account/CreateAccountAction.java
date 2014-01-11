/**
 * 
 */
package com.epam.devteam.action.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.epam.devteam.util.property.PropertyManager;

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
	HttpSession session;
	DaoFactory factory;
	UserDao dao;
	User user;
	String email;
	String password1;
	String password2;
	UserRole role;
	
	session = request.getSession();
	email = request.getParameter("email");
	password1 = request.getParameter("password1");
	password2 = request.getParameter("password2");
	try {
	    role = UserRole.valueOf(request.getParameter("role"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role was detected: "
		    + request.getParameter("role"));
	    throw new ActionException();
	}
	if (!isFormValuesValid(session, email, password1, password2)) {
	    return request.getHeader("referer");
	}
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	dao = factory.getUserDao();
	switch (role) {
	case CUSTOMER:
	    user = new Customer();
	    break;
	default:
	    user = new Employee();
	    break;
	}
	user.setEmail(email);
	user.setPassword(password1);
	user.setRole(role);
	try {
	    dao.create(user);
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be created.");
	    throw new ActionException();
	}
	LOGGER.debug("Account " + email + " has been created.");
	session.setAttribute("success", "account.create.success");
	return "success";
    }

    private boolean isFormValuesValid(HttpSession session, String email,
	    String password1, String password2) throws ActionException {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String emailRegex;
	if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
	    session.setAttribute("error", "account.error.fieldsNotCorrect");
	    return false;
	}
	if (!password1.equals(password2)) {
	    session.setAttribute("error",
		    "account.create.error.passwordConfirm");
	    return false;
	}
	try {
	    propertyManager = PropertyManager.getInstance();
	    emailRegex = propertyManager.getString("validation.email");
	    pattern = Pattern.compile(emailRegex);
	    matcher = pattern.matcher(email);
	    if (!matcher.matches()) {
		session.setAttribute("error",
			"account.create.error.emailNotValid");
		return false;
	    }
	} catch (Exception e) {
	    LOGGER.warn("Email cannot be validate.");
	    throw new ActionException();
	}
	return true;
    }

}
