package com.epam.devteam.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.devteam.entity.User;
import com.epam.devteam.entity.UserRole;

public class SecurityFilter implements Filter {
    private Map<String, UserRole> actions = new HashMap<String, UserRole>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

	actions.put("GET/", UserRole.UNAUTHORIZED_USER);
	actions.put("GET/main", UserRole.UNAUTHORIZED_USER);
	actions.put("GET/error", UserRole.UNAUTHORIZED_USER);
	actions.put("GET/success", UserRole.UNAUTHORIZED_USER);
	actions.put("POST/signin", UserRole.UNAUTHORIZED_USER);
	actions.put("GET/signout", UserRole.AUTHORIZED_USER);
	actions.put("GET/user-account", UserRole.UNAUTHORIZED_USER);
	actions.put("POST/edit-account", UserRole.AUTHORIZED_USER);
	actions.put("POST/create-account", UserRole.UNAUTHORIZED_USER);
	actions.put("POST/save-account", UserRole.AUTHORIZED_USER);
	actions.put("GET/manage-accounts", UserRole.ADMINISTRATOR);
	actions.put("POST/manage-account", UserRole.ADMINISTRATOR);
	actions.put("GET/manage-account", UserRole.ADMINISTRATOR);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	System.out.println("Hello from filter");
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	HttpSession session = httpRequest.getSession();
	User user = (User) session.getAttribute("user");
	UserRole role = actions.get(httpRequest.getRequestURI());
	System.out.println(httpRequest.getRequestURI());
	if (role == null) {
	    chain.doFilter(request, response);
	    return;
	}
	System.out.println(role);
	switch(role){
	case ADMINISTRATOR:
	    if(!user.getRole().equals(role)){
		httpRequest.getRequestDispatcher("main").forward(httpRequest, httpResponse);
	    }
	    break;
	}
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

}
