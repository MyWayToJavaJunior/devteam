/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.entity.user.User;

/**
 * @date Jan 6, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class SignoutAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	switch (user.getRole()) {
	case CUSTOMER:
	case MANAGER:
	    request.getSession().removeAttribute("orders");
	    request.getSession().removeAttribute("order");
	    request.getSession().removeAttribute("feedback");
	    break;
	case ADMINISTRATOR:
	    request.getSession().removeAttribute("users");
	    request.getSession().removeAttribute("accountToManage");
	    break;
	default:
	    break;
	}
	request.getSession().removeAttribute("user");
	return "main";
    }
}
