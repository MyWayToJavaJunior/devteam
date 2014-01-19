package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionDatabaseFailException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;
import com.epam.devteam.util.validator.RequestFieldsValidator;
import com.epam.devteam.util.validator.ValidationException;

/**
 * The <code>ChangeAccountPasswordAction</code> is used to set new account
 * password. At first it checks old password. If old password is correct new
 * password will be set.
 * 
 * @date Jan 17, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ChangeAccountPasswordAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(CreateAccountAction.class);
    private DaoFactory factory;
    private UserDao dao;

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
	User user = (User) session.getAttribute("user");
	String tempId = request.getParameter("id");
	UserRole userRole = user.getRole();
	String oldPassword = request.getParameter("old-password");
	String newPassword1 = request.getParameter("new-password1");
	String newPassword2 = request.getParameter("new-password2");
	int id = 0;
	boolean passwordFieldsNull = false;
	boolean newPasswordEmpty = false;
	boolean newPasswordValid = false;
	try {
	    id = Integer.parseInt(tempId);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Id field is not valid.");
	    throw new ActionBadRequestException();
	}
	session.setAttribute("oldPassword", oldPassword);
	session.setAttribute("newPassword1", newPassword1);
	session.setAttribute("newPassword2", newPassword2);
	if (!userRole.equals(UserRole.ADMINISTRATOR)) {
	    if (!user.getPassword().equals(oldPassword)) {
		session.removeAttribute("passwordError");
		session.setAttribute("oldPasswordError",
			"account.oldPasswordWrong");
		LOGGER.debug("Old password value is wrong.");
		return new ActionResult(ActionResult.METHOD.REDIRECT,
			request.getHeader("referer"));
	    }
	    session.removeAttribute("oldPasswordError");

	}
	passwordFieldsNull = RequestFieldsValidator.equalNull(newPassword1,
		newPassword2);
	if (passwordFieldsNull) {
	    LOGGER.warn("Old password field is not valid.");
	    throw new ActionBadRequestException();
	}
	newPasswordEmpty = RequestFieldsValidator.empty(newPassword1);
	if (newPasswordEmpty) {
	    session.setAttribute("passwordError", "account.passwordEmpty");
	    LOGGER.debug("New password value is empty.");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	try {
	    newPasswordValid = RequestFieldsValidator
		    .passwordValid(newPassword1);
	} catch (ValidationException e) {
	    LOGGER.debug("Password cannot be validated.");
	    throw new ActionException();
	}
	if (!newPasswordValid) {
	    session.setAttribute("passwordError", "account.passwordNotValid");
	    LOGGER.debug("New password value is not valid.");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	if (!newPassword1.equals(newPassword2)) {
	    session.setAttribute("passwordError", "account.passwordsDontMatch");
	    LOGGER.debug("Passwords don't match.");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	try {
	    userDao().updatePassword(id, newPassword1);
	    LOGGER.debug("Password has been changed.");
	} catch (DaoException e) {
	    LOGGER.warn("Password cannot be changed.");
	    throw new ActionDatabaseFailException(e);
	}
	if (user.getId() == id) {
	    user.setPassword(newPassword1);
	    session.setAttribute("user", user);
	}
	session.setAttribute("success", "account.changePasswordSuccess");
	session.setAttribute("link", "do/edit-account?id=" + id);
	session.removeAttribute("oldPassword");
	session.removeAttribute("newPassword1");
	session.removeAttribute("newPassword2");
	session.removeAttribute("oldPasswordError");
	session.removeAttribute("emailError");
	session.removeAttribute("passwordError");
	session.removeAttribute("error");

	return new ActionResult(ActionResult.METHOD.REDIRECT, "success");
    }

    /**
     * Is used to get dao factory. It initializes factory during the first use.
     * 
     * @return Dao factory.
     * @throws DaoException If something fails.
     */
    private DaoFactory daoFactory() throws DaoException {
	if (factory == null) {
	    factory = DaoFactory.getDaoFactory();
	}
	return factory;
    }

    /**
     * Is used to get user dao. It initializes dao during the first use.
     * 
     * @return The user.
     * @throws DaoException If something fails.
     */
    private UserDao userDao() throws DaoException {
	if (dao == null) {
	    dao = daoFactory().getUserDao();
	}
	return dao;
    }
}
