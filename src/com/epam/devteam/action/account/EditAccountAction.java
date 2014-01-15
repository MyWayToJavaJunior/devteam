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
import com.epam.devteam.entity.user.UserRole;

/**
 * The <code>EditAccountAction</code> is used to save changes to database. User
 * attribute in session is to be updated after saving in database.
 * 
 * @date Jan 7, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class EditAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(EditAccountAction.class);
    private DaoFactory factory;
    private UserDao dao;

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

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	User currentUser = (User) session.getAttribute("user");
	User account;
	int accountId = 0;
	try {
	    accountId = Integer.parseInt(request.getParameter("id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Illegal id is defined");
	    throw new ActionBadRequestException();
	}
	if (!currentUser.getRole().equals(UserRole.ADMINISTRATOR)) {
	    if (!currentUser.getId().equals(userId)) {
		LOGGER.debug("Simple users cannot edit other accounts");
		session.setAttribute("error", "error.badRequest");
		return new ActionResult(ActionResult.METHOD.REDIRECT, "error");
	    }
	}
	DaoFactory factory;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao cannot be created.");
	    throw new ActionException();
	}
	UserDao dao = factory.getUserDao();
	try {
	    account = dao.find(accountId);
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionException();
	}
	session.setAttribute("account", account);
	return new ActionResult(ActionResult.METHOD.REDIRECT, "account");
    }
}
