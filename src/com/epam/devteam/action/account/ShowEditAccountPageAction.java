package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionDatabaseFailException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;

/**
 * The <code>ShowEditAccountPageAction</code> returns edit-account.jsp page's
 * name and method to show page.
 * 
 * @date Jan 15, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowEditAccountPageAction implements Action {
    private final static Logger LOGGER = Logger
	    .getLogger(ShowEditAccountPageAction.class);
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
	User user = (User) session.getAttribute("user");
	String stringAccountId = request.getParameter("id");
	User account;
	int accountId = 0;
	LOGGER.debug("Show edit account page action...");
	if (stringAccountId == null || stringAccountId.isEmpty()) {
	    LOGGER.debug("Id is empty.");
	    session.setAttribute("error", "error.badRequest");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	try {
	    accountId = Integer.parseInt(stringAccountId);
	} catch (IllegalArgumentException e) {
	    LOGGER.debug("Id format is wrong.");
	    session.setAttribute("error", "error.badRequest");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
	    try {
		account = userDao().find(accountId);
	    } catch (DaoException e) {
		LOGGER.warn("User cannot be found because of database fail.");
		throw new ActionDatabaseFailException(e);
	    }
	} else {
	    if (user.getId() != accountId) {
		LOGGER.debug("User " + user.getEmail()
			+ " tried to edit other user's account.");
		session.setAttribute("error", "error.badRequest");
		return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	    }
	    account = user;
	}
	LOGGER.debug(account);
	session.setAttribute("account", account);
	session.removeAttribute("error");
	return new ActionResult(ActionResult.METHOD.FORWARD, "edit-account");
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
