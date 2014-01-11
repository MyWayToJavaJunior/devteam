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
	actions.put("GET/create-order", UserRole.CUSTOMER);
	actions.put("POST/create-order", UserRole.CUSTOMER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	HttpSession session = httpRequest.getSession();
	User user = (User) session.getAttribute("user");
	// System.out.println(httpRequest.getRequestURI());
	UserRole role = actions.get(httpRequest.getMethod()
		+ httpRequest.getPathInfo());
	if (role == null) {
	    chain.doFilter(httpRequest, httpResponse);
	    return;
	}
	if (user == null) {
	    if (role.equals(UserRole.UNAUTHORIZED_USER)) {
		chain.doFilter(httpRequest, httpResponse);
		return;
	    } else {
		session.setAttribute("error", "accessDenied");
		httpResponse.sendRedirect("error");
		return;
	    }
	}
	switch (role) {
	case ADMINISTRATOR:
	    if (!user.getRole().equals(role)) {
		session.setAttribute("error", "action.accessDenied");
		httpResponse.sendRedirect("error");
	    }
	    break;
	case CUSTOMER:
	    if (!user.getRole().equals(role)) {
		session.setAttribute("error", "action.accessDenied");
		httpResponse.sendRedirect("error");
	    }
	    break;
	default:
	    chain.doFilter(httpRequest, httpResponse);
	    return;
	}
	chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }

}
