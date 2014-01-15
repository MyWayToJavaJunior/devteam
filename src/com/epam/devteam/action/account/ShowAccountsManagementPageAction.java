package com.epam.devteam.action.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.user.User;

public class ShowAccountsManagementPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowAccountsManagementPageAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {

	List<User> users;
	DaoFactory factory;
	UserDao userDao;
	try {
	    factory = DaoFactory.getDaoFactory();
	    userDao = factory.getUserDao();
	} catch (DaoException e) {
	    LOGGER.warn("Dao cannot be created.");
	    throw new ActionException();
	}
	try {
	    users = userDao.list();
	} catch (DaoException e) {
	    LOGGER.warn("Request cannot be executed.");
	    throw new ActionException();
	}
	HttpSession session = request.getSession();
	session.setAttribute("users", users);
	return "manage-accounts";
    }

}
