package com.epam.devteam.action.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.entity.User;

public class ManageAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ManageAccountAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	@SuppressWarnings("unchecked")
	List<User> users = (List<User>) session.getAttribute("users");
	Integer accountId;
	try {
	    accountId = Integer.parseInt(request.getParameter("account-id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Illegal id is defined");
	    throw new ActionException();
	}
	for (User user : users) {
	    if (user.getId().equals(accountId)) {
		session.setAttribute("accountToManage", user);
	    }
	}
	return "manage-account";
    }

}
