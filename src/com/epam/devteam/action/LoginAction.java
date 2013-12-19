/**
 * 
 */
package com.epam.devteam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.User;

/**
 * @date Dec 14, 2013
 * @author anjey
 * 
 */
public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	if ("login".equals(request.getParameter("command"))) {
	    System.out.println(request.getParameter("username"));
	    System.out.println(request.getParameter("password"));
	    System.out.println("Welcome user!");
	    return "home";
	}
	try {
	    DaoFactory df = DaoFactory.takeDaoFactory();
	    UserDao ud = df.takeUserDao();
	    List<User> users = ud.listUsers();
	    for (User user : users) {
		System.out.println(user);
	    }
	} catch (Exception e) {
	    System.out.println("You fucking failed!");
	    e.printStackTrace();
	}

	return "login";
    }
}
