/**
 * 
 */
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
import com.epam.devteam.entity.user.Customer;
import com.epam.devteam.entity.user.Employee;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;
import com.epam.devteam.service.validation.RequestFieldsValidator;
import com.epam.devteam.service.validation.ValidationException;

/**
 * The <code>CreateAccountAction</code> class is used to save new user to
 * database. If every field is available new user will save to database and will
 * save in session. Success message contains a link to edit new account. If new
 * user was created by administrator success message will contain link to manage
 * new account. Implements <code>Action</code> interface.
 * 
 * @date Jan 4, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class CreateAccountAction implements Action {
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
	User user;
	UserRole role;
	HttpSession session = request.getSession();
	User currentUser = (User) session.getAttribute("user");
	String email = request.getParameter("email");
	String password1 = request.getParameter("password1");
	String password2 = request.getParameter("password2");
	String stringRole = request.getParameter("role");
	int id = 0;
	boolean formFieldsEqualNull = false;
	boolean emailEmpty = false;
	boolean emailValid = false;
	boolean passwordEmpty = false;
	boolean passwordValid = false;
	LOGGER.debug("Create user action...");
	formFieldsEqualNull = RequestFieldsValidator.equalNull(stringRole,
		email, password1, password2);
	if (formFieldsEqualNull) {
	    LOGGER.warn("Form fields are not valid.");
	    throw new ActionBadRequestException();
	}
	try {
	    role = UserRole.valueOf(stringRole);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role: " + stringRole);
	    throw new ActionBadRequestException(e);
	}
	session.setAttribute("email", email);
	session.setAttribute("password1", password1);
	session.setAttribute("password2", password2);
	session.removeAttribute("emailError");
	session.removeAttribute("passwordError");
	emailEmpty = RequestFieldsValidator.empty(email);
	if (emailEmpty) {
	    session.setAttribute("emailError", "account.emailEmpty");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    emailValid = RequestFieldsValidator.emailValid(email);
	} catch (ValidationException e) {
	    LOGGER.warn("Email " + email + " cannot be validated.");
	    throw new ActionException(e);
	}
	if (!emailValid) {
	    session.setAttribute("emailError", "account.emailNotValid");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    user = userDao().find(email);
	} catch (DaoException e) {
	    LOGGER.warn("User existence cannot be checked.");
	    throw new ActionDatabaseFailException(e);
	}
	if (user != null) {
	    session.setAttribute("error", "account.userAlreadyExists");
	    LOGGER.debug("User " + email + " is already exist");
	    return new ActionResult(ActionResult.METHOD.REDIRECT, "error");
	}
	passwordEmpty = RequestFieldsValidator.empty(password1);
	if (passwordEmpty) {
	    session.setAttribute("passwordError", "account.passwordEmpty");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    passwordValid = RequestFieldsValidator.passwordValid(password1);
	} catch (ValidationException e) {
	    LOGGER.warn("Password cannot be validated.");
	    throw new ActionException(e);
	}
	if (!passwordValid) {
	    session.setAttribute("passwordError", "account.passwordNotValid");
	    LOGGER.debug("Password is not valid");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	if (!password1.equals(password2)) {
	    session.setAttribute("passwordError", "account.passwordsDontMatch");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	switch (role) {
	case CUSTOMER:
	    user = new Customer();
	    break;
	default:
	    user = new Employee();
	    break;
	}
	user.setEmail(email);
	user.setPassword(password1);
	user.setRole(role);
	try {
	    id = userDao().createWithIdReturn(user);
	    user.setId(id);
	    LOGGER.debug("User " + user.getEmail()
		    + " has been created. User id: " + user.getId());
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be created.");
	    throw new ActionDatabaseFailException(e);
	}
	if (currentUser == null) {
	    session.setAttribute("user", user);
	}
	session.setAttribute("success", "account.createSuccess");
	session.setAttribute("link", "do/edit-account?id=" + user.getId());
	session.removeAttribute("email");
	session.removeAttribute("password1");
	session.removeAttribute("password2");
	session.removeAttribute("error");
	session.removeAttribute("emailError");
	session.removeAttribute("passwordError");
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
