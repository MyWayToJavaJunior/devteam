/**
 * 
 */
package com.epam.devteam.action.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.entity.user.Customer;
import com.epam.devteam.entity.user.Employee;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;
import com.epam.devteam.util.property.PropertyManager;

/**
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
	HttpSession session;
	User user;
	String email;
	String password1;
	String password2;
	String stringRole;
	UserRole role;
	boolean formFieldsValid = false;
	boolean userAlreadyExists = false;
	boolean emailValid = false;
	boolean passwordValid = false;

	LOGGER.debug("Action starts.");
	email = request.getParameter("email");
	password1 = request.getParameter("password1");
	password2 = request.getParameter("password2");
	stringRole = request.getParameter("role");
	formFieldsValid = isFormFieldsValid(email, password1, password2,
		stringRole);
	if (!formFieldsValid) {
	    LOGGER.warn("Form fields are not valid.");
	    throw new ActionException();
	}
	try {
	    role = UserRole.valueOf(stringRole);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role was defined: " + stringRole);
	    throw new ActionException(e);
	}
	try {
	    emailValid = isEmailValid(request, email);
	} catch (Exception e) {
	    LOGGER.warn("Email " + email + " validaition failed.");
	    throw new ActionException(e);
	}
	if (!emailValid) {
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	try {
	    passwordValid = isPasswordValid(request, password1, password2);
	} catch (Exception e) {
	    LOGGER.warn("Password validaition failed.");
	}
	if (!passwordValid) {
	    LOGGER.debug("Password is not valid");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    userAlreadyExists = userDao().containsUser(email);
	} catch (DaoException e) {
	    LOGGER.warn("User existence cannot be determined.");
	    throw new ActionException(e);
	}
	if (userAlreadyExists) {
	    LOGGER.debug("User " + email + " is already exist");
	    request.setAttribute("error", "error.account.userAlreadyExist");
	    return new ActionResult(ActionResult.METHOD.REDIRECT, "error");
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
	    userDao().create(user);
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be created.");
	    throw new ActionException(e);
	}
	LOGGER.debug("Account " + email + " has been created.");
	session = request.getSession();
	session.setAttribute("user", user);
	request.setAttribute("success", "account.createSuccess");
	request.setAttribute("link", "do/edit-account");
	return new ActionResult(ActionResult.METHOD.REDIRECT, "success");
    }

    /**
     * Is used to check whether all form fields are valid.
     * 
     * @param email The user email field
     * @param password1 The password field
     * @param password2 The password confirm
     * @param role The user role
     * @return True if fields are valid, false otherwise.
     */
    private boolean isFormFieldsValid(String email, String password1,
	    String password2, String role) {
	boolean result = true;
	if (email == null) {
	    LOGGER.debug("Email field is not valid.");
	    result = false;
	}
	if (password1 == null) {
	    LOGGER.debug("Password1 field is not valid.");
	    result = false;
	}
	if (password2 == null) {
	    LOGGER.debug("Password2 field is not valid.");
	    result = false;
	}
	return result;
    }

    /**
     * Is used to check whether email value is valid. If email is not valid
     * "email-error" attribute will be set to the request.
     * 
     * @param request The http request.
     * @param email The user email.
     * @return True if email is valid, false otherwise.
     * @throws Exception If property manager cannot be taken or email pattern is
     *             not valid
     */
    private boolean isEmailValid(HttpServletRequest request, String email)
	    throws Exception {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String emailRegex;
	if (email.isEmpty()) {
	    LOGGER.debug("User email is empty.");
	    request.setAttribute("emailError", "account.emailEmpty");
	    return false;
	}
	propertyManager = PropertyManager.getInstance();
	emailRegex = propertyManager.getString("validation.email");
	pattern = Pattern.compile(emailRegex);
	matcher = pattern.matcher(email);
	if (matcher.matches()) {
	    return true;
	} else {
	    LOGGER.debug("User email " + email + " is not valid");
	    request.setAttribute("emailError", "account.emailNotValid");
	    return false;
	}
    }

    /**
     * Is used to check whether password value is valid. If email is not valid
     * "password-error" attribute will be set to the request.
     * 
     * @param request The http request.
     * @param password1 The user password.
     * @param password2 The user password confirmation.
     * @return True if password is valid, false otherwise.
     * @throws Exception If property manager cannot be taken or email pattern is
     *             not valid
     */
    private boolean isPasswordValid(HttpServletRequest request,
	    String password1, String password2) throws Exception {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String passwordRegex;
	if (password1.isEmpty()) {
	    LOGGER.debug("User password is empty.");
	    request.setAttribute("passwordError", "account.passwordEmpty");
	    return false;
	}
	propertyManager = PropertyManager.getInstance();
	passwordRegex = propertyManager.getString("validation.password");
	pattern = Pattern.compile(passwordRegex);
	matcher = pattern.matcher(password1);
	if (matcher.matches()) {
	    LOGGER.debug("User password is not valid.");
	    request.setAttribute("passwordError", "account.passwordNotValid");
	    return false;
	}
	if (!password1.equals(password2)) {
	    LOGGER.debug("Passwords don't match.");
	    request.setAttribute("passwordConfirmError",
		    "account.passwordsDontMatch");
	    return false;
	}
	return true;
    }
}
