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
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.User;

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
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	if (!isFormValuesValid(email, password)) {
	    session.setAttribute("error",
		    "account.signin.error.fieldsNotCorrect");
	    LOGGER.debug("Sign in form fields are not valid.");
	    return "main";
	}
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao cannot be created.");
	    throw new ActionException();
	}
	UserDao userDao = factory.getUserDao();
	User user;
	try {
	    user = userDao.find(email, password);
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionException();
	}
	if (user == null) {
	    session.setAttribute("error", "account.create.error.notFound");
	    LOGGER.debug("Account" + email + "is not found.");
	} else {
	    session.setAttribute("user", user);
	    LOGGER.debug("User " + user.getEmail() + " has been registered.");
	}
	return "main";
    }

    private boolean isFormValuesValid(String email, String password) {
	if (email.isEmpty()) {
	    return false;
	}
	if (password.isEmpty()) {
	    return false;
	}
	return true;
    }

}
