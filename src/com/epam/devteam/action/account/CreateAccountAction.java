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
import com.epam.devteam.util.property.PropertyManager;

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
	boolean formFieldsValid = false;
	boolean emailValid = false;
	boolean passwordValid = false;
	LOGGER.debug("Create user action...");
	formFieldsValid = areFormFieldsValid(email, password1, password2,
		stringRole);
	if (!formFieldsValid) {
	    LOGGER.warn("Form fields are not valid.");
	    throw new ActionBadRequestException();
	}
	try {
	    role = UserRole.valueOf(stringRole);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role was defined: " + stringRole);
	    throw new ActionBadRequestException(e);
	}
	session.setAttribute("email", email);
	session.setAttribute("password1", password1);
	session.setAttribute("password2", password2);
	try {
	    emailValid = isEmailValid(session, email);
	} catch (Exception e) {
	    LOGGER.warn("Email " + email + " validaition failed.");
	    throw new ActionException(e);
	}
	if (!emailValid) {
	    session.setAttribute("emailError", "account.emailEmpty");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    passwordValid = isPasswordValid(session, password1, password2);
	} catch (Exception e) {
	    LOGGER.warn("Password validaition failed.");
	    throw new ActionException(e);
	}
	if (!passwordValid) {
	    LOGGER.debug("Password is not valid");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    "create-account");
	}
	try {
	    user = userDao().find(email);
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be found because of database fail.");
	    throw new ActionDatabaseFailException(e);
	}
	if (user != null) {
	    session.setAttribute("error", "account.userAlreadyExist");
	    LOGGER.debug("User " + email + " is already exist");
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
	    id = userDao().createWithIdReturn(user);
	    user.setId(id);
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be created because of database fail.");
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
	LOGGER.debug("User " + user.getEmail() + " has been created. User id: "
		+ user.getId());
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
    private boolean areFormFieldsValid(String email, String password1,
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
    private boolean isEmailValid(HttpSession session, String email)
	    throws Exception {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String emailRegex;
	if (email.isEmpty()) {
	    session.setAttribute("emailError", "account.emailEmpty");
	    LOGGER.debug("User email is empty.");
	    return false;
	}
	propertyManager = PropertyManager.getInstance();
	emailRegex = propertyManager.getString("validation.email");
	pattern = Pattern.compile(emailRegex);
	matcher = pattern.matcher(email);
	if (!matcher.matches()) {
	    session.setAttribute("emailError", "account.emailNotValid");
	    LOGGER.debug("User email " + email + " is not valid");
	    return false;
	}
	session.removeAttribute("emailError");
	return true;
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
    private boolean isPasswordValid(HttpSession session, String password1,
	    String password2) throws Exception {
	Pattern pattern;
	Matcher matcher;
	PropertyManager propertyManager;
	String passwordRegex;
	if (password1.isEmpty()) {
	    session.setAttribute("passwordError", "account.passwordEmpty");
	    LOGGER.debug("User password is empty.");
	    return false;
	}
	propertyManager = PropertyManager.getInstance();
	passwordRegex = propertyManager.getString("validation.password");
	pattern = Pattern.compile(passwordRegex);
	matcher = pattern.matcher(password1);
	if (matcher.matches()) {
	    session.setAttribute("passwordError", "account.passwordNotValid");
	    LOGGER.debug("User password is not valid.");
	    return false;
	}
	session.removeAttribute("passwordError");
	if (!password1.equals(password2)) {
	    session.setAttribute("passwordConfirmError",
		    "account.passwordsDontMatch");
	    LOGGER.debug("Passwords don't match.");
	    return false;
	}
	session.removeAttribute("passwordConfirmError");
	return true;
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
