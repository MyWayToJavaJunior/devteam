package com.epam.devteam.filter;

import java.io.IOException;
import java.util.EnumSet;
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

import com.epam.devteam.action.ShowAboutUsPageAction;
import com.epam.devteam.action.ShowContactsPageAction;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;

public class SecurityFilter implements Filter {
    private Map<String, EnumSet<UserRole>> actions = new HashMap<String, EnumSet<UserRole>>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	EnumSet<UserRole> all = EnumSet.of(UserRole.UNREGISTERED_USER,
		UserRole.CUSTOMER, UserRole.DEVELOPER, UserRole.MANAGER,
		UserRole.ADMINISTRATOR);
	EnumSet<UserRole> authorized = EnumSet.of(UserRole.CUSTOMER,
		UserRole.DEVELOPER, UserRole.MANAGER, UserRole.ADMINISTRATOR);
	EnumSet<UserRole> customer = EnumSet.of(UserRole.CUSTOMER);
	EnumSet<UserRole> employee = EnumSet.of(UserRole.DEVELOPER,
		UserRole.MANAGER, UserRole.ADMINISTRATOR);
	EnumSet<UserRole> administrator = EnumSet.of(UserRole.ADMINISTRATOR);
	EnumSet<UserRole> manager = EnumSet.of(UserRole.MANAGER);
	actions.put("GET/main", all);
	actions.put("GET/contacts", all);
	actions.put("GET/about-us", all);
	actions.put("GET/error", all);
	actions.put("GET/success", all);
	actions.put("POST/set-language", all);
	actions.put("POST/signin", all);
	actions.put("GET/signout", authorized);
	actions.put("GET/create-account", all);
	actions.put("POST/create-account", all);
	actions.put("GET/edit-account", authorized);
	actions.put("POST/save-account", authorized);
	actions.put("GET/manage-accounts", authorized);
	actions.put("GET/deactivate-account", administrator);
	actions.put("GET/activate-account", administrator);
	actions.put("POST/change-password", authorized);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	HttpSession session = httpRequest.getSession();
	EnumSet<UserRole> allowedRoles = actions.get(httpRequest.getMethod()
		+ httpRequest.getPathInfo());
	System.out.println(httpRequest.getMethod() + httpRequest.getPathInfo());
	User currentUser = (User) session.getAttribute("user");
	UserRole currentUserRole;
	if (currentUser == null) {
	    currentUserRole = UserRole.UNREGISTERED_USER;
	} else {
	    currentUserRole = currentUser.getRole();
	}
	if (allowedRoles == null) {
	    chain.doFilter(request, response);
	    return;
	}
	if (!allowedRoles.contains(currentUserRole)) {
	    session.setAttribute("error", "error.accessDenied");
	    request.getRequestDispatcher("error").forward(request, response);
	    return;
	}
	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
