package com.epam.devteam.action.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;

/**
 * The <code>SetLanguageAction</code> to set localization.
 * 
 * @date Jan 13, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class SetLanguageAction implements Action {

    /**
     * Is used to perform required actions and define method and view for
     * <code>Controller</code>. Returns result as <code>ActionResult</code>.
     * 
     * @param request Request to process.
     * @param response Response to send.
     * @return ActionResult where to redirect user
     * @throws ActionException If something fails during method performing.
     */
    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session = request.getSession();
	String locale = request.getParameter("locale");
	Config.set(session, Config.FMT_LOCALE, new java.util.Locale(locale));
	return new ActionResult(ActionResult.METHOD.REDIRECT,
		request.getHeader("referer"));
    }

}
