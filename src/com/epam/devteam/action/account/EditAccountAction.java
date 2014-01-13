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
import com.epam.devteam.entity.user.User;

/**
 * @date Jan 7, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class EditAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(EditAccountAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	Integer accountId;
	try {
	    accountId = Integer.parseInt(request.getParameter("account-id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Illegal id is defined");
	    throw new ActionException();
	}
	User account = (User) session.getAttribute("account");
	if (account.getId().equals(accountId)) {
	    return "account";
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
	return "account";
    }
}
