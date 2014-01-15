package com.epam.devteam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.epam.devteam.action.exception.ActionException;

public class SetLanguageAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	String locale = request.getParameter("locale");
	Config.set(session, Config.FMT_LOCALE, new java.util.Locale(locale));
	return request.getHeader("referer");
    }

}
