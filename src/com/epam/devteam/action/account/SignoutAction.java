/**
 * 
 */
package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.entity.user.User;

/**
 * @date Jan 6, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class SignoutAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	switch (user.getRole()) {
	case CUSTOMER:
	case MANAGER:
	    session.removeAttribute("orders");
	    session.removeAttribute("order");
	    session.removeAttribute("feedback");
	    break;
	case ADMINISTRATOR:
	    session.removeAttribute("users");
	    session.removeAttribute("accountToManage");
	    break;
	default:
	    break;
	}
	session.removeAttribute("user");
	session.removeAttribute("error");
	session.removeAttribute("success");
	return new ActionResult(ActionResult.METHOD.FORWARD, "main");
    }
}
