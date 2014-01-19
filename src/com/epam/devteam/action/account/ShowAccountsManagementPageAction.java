package com.epam.devteam.action.account;

import java.util.List;

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
import com.epam.devteam.util.validator.RequestFieldsValidator;

/**
 * The <code>ShowAccountsManagementPageAction</code> is used to show user
 * accounts management page.
 * 
 * @date Jan 17, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowAccountsManagementPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowAccountsManagementPageAction.class);
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
	List<User> users;
	String tempFirstRow = (String) request.getParameter("first-row");
	String tempRowNumber = (String) request.getParameter("row-number");
	int firstRow = 0;
	int rowNumber = 0;
	if (RequestFieldsValidator.equalNull(tempFirstRow, tempRowNumber)
		|| RequestFieldsValidator.empty(tempFirstRow, tempRowNumber)) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Form fields are not valid: equas null");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	try {
	    firstRow = Integer.parseInt(tempFirstRow);
	    rowNumber = Integer.parseInt(tempRowNumber);
	} catch (IllegalArgumentException e) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Form fields are not valid: not a number");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	if ((rowNumber % 5 != 0) || (rowNumber > 50)) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Row number is not valid");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	try {
	    users = userDao().list(firstRow, rowNumber);
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionDatabaseFailException(e);
	}
	session.setAttribute("users", users);
	return new ActionResult(ActionResult.METHOD.FORWARD, "manage-accounts");
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
