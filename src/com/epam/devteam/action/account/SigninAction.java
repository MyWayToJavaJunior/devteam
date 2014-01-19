/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.util.validator.RequestFieldsValidator;

/**
 * The <code>SigninAction</code> is used to sign in.
 * 
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class SigninAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(SigninAction.class);
    private DaoFactory factory;
    private UserDao dao;

    /**
     * Is used to perform required actions and define method and view for
     * <code>Controller</code>. Returns result as <code>ActionResult</code>.
     * 
     * @param request Request to process.
     * @param response Response to send.
     * @return ActionResult where to redirect user
     * @throws ActionException If something fails during method performing.
     */
    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	User user;
	boolean fieldsEqualNull = false;
	boolean fieldsEmpty = false;
	fieldsEqualNull = RequestFieldsValidator.equalNull(email, password);
	if (fieldsEqualNull) {
	    LOGGER.debug("Sign in form fields are not valid.");
	    throw new ActionBadRequestException();
	}
	fieldsEmpty = RequestFieldsValidator.empty(email, password);
	if (fieldsEmpty) {
	    session.setAttribute("signInError", "account.enterEmailAndPassword");
	    LOGGER.debug("Email or/and password are empty.");
	    return new ActionResult(ActionResult.METHOD.REDIRECT, "main");
	}
	session.setAttribute("email", email);
	try {
	    user = userDao().find(email, password);
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionBadRequestException(e);
	}
	if (user == null) {
	    session.setAttribute("signInError", "account.accountNotFound");
	    LOGGER.debug("Email or/and password are empty.");
	    return new ActionResult(ActionResult.METHOD.REDIRECT, "main");
	}
	session.setAttribute("user", user);
	session.removeAttribute("email");
	session.removeAttribute("signInError");
	LOGGER.debug("User " + user.getEmail() + " has been registered.");
	return new ActionResult(ActionResult.METHOD.REDIRECT, "main");
    }

    /**
     * Is used to get dao factory. It initializes factory during the first use.
     * 
     * @return Dao factory.
     * @throws DaoException If something fails.
     */
    private DaoFactory daoFactory() throws DaoException {
	if (factory == null) {
	    factory = DaoFactory.getDaoFactory();
	}
	return factory;
    }

    /**
     * Is used to get user dao. It initializes dao during the first use.
     * 
     * @return The user.
     * @throws DaoException If something fails.
     */
    private UserDao userDao() throws DaoException {
	if (dao == null) {
	    dao = daoFactory().getUserDao();
	}
	return dao;
    }
}
