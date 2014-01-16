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
import com.epam.devteam.service.validation.FieldType;
import com.epam.devteam.service.validation.RequestFieldsValidator;
import com.epam.devteam.service.validation.ValidationException;

/**
 * The <code>SaveAccountAction</code> is used to save account changes.
 * 
 * @date Jan 8, 2014
 * @author Andrey Kovalskiy
 */
public class SaveAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(SaveAccountAction.class);
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
	User user;
	User account;
	UserRole role;
	String tempId = request.getParameter("id");
	String tempRole = request.getParameter("role");
	String tempActive = request.getParameter("is-active");
	String firstName = request.getParameter("first-name");
	String lastName = request.getParameter("last-name");
	String company = request.getParameter("company");
	String position = request.getParameter("position");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	String qualification = request.getParameter("qualification");
	int id = 0;
	boolean active = false;
	boolean fieldsEqualsNull = false;
	boolean fieldsEmpty = false;
	boolean fieldsLengthValid = false;
	fieldsEqualsNull = RequestFieldsValidator.equalsNull(tempId, tempRole,
		tempActive);
	if (fieldsEqualsNull) {
	    LOGGER.warn("Fields are not valid: null");
	    throw new ActionBadRequestException();
	}
	fieldsEmpty = RequestFieldsValidator
		.empty(tempId, tempRole, tempActive);
	if (fieldsEmpty) {
	    LOGGER.warn("Fields are not valid: empty");
	    throw new ActionBadRequestException();
	}
	try {
	    id = Integer.parseInt(tempId);
	} catch (IllegalArgumentException e) {
	    LOGGER.debug("Id format is wrong.");
	    throw new ActionBadRequestException(e);
	}
	try {
	    role = UserRole.valueOf(tempRole);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Unknown role: " + tempRole);
	    throw new ActionBadRequestException(e);
	}
	try {
	    active = Boolean.parseBoolean(tempActive);
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Activity status is wrong: " + tempActive);
	    throw new ActionBadRequestException(e);
	}
	try {
	    switch (role) {
	    case CUSTOMER:
		fieldsLengthValid = RequestFieldsValidator.lengthValid(
			FieldType.INPUT_TEXT, firstName, lastName, company,
			position, address, phone);
		break;
	    default:
		fieldsLengthValid = RequestFieldsValidator.lengthValid(
			FieldType.INPUT_TEXT, firstName, lastName,
			qualification, address, phone);
		break;
	    }
	} catch (ValidationException e) {
	    LOGGER.warn("Fields cannot be validated.");
	    throw new ActionBadRequestException(e);
	}
	if (!fieldsLengthValid) {
	    LOGGER.warn("Fields length is not valid.");
	    throw new ActionBadRequestException();
	}
	switch (role) {
	case CUSTOMER:
	    Customer customer = new Customer();
	    customer.setCompany(company);
	    customer.setPosition(position);
	    account = customer;
	    break;
	default:
	    Employee employee = new Employee();
	    employee.setQualification(qualification);
	    account = employee;
	    break;
	}
	account.setId(id);
	account.setRole(role);
	account.setActive(active);
	account.setFirstName(firstName);
	account.setLastName(lastName);
	// DateManager.dateFromRequest(request);
	account.setBirthDate(null);
	account.setAddress(address);
	account.setPhone(phone);
	try {
	    userDao().update(account);
	} catch (DaoException e) {
	    LOGGER.warn("Customer cannot be updated.");
	    throw new ActionDatabaseFailException();
	}
	user = (User) session.getAttribute("user");
	if (user.getId().equals(account.getId())) {
	    session.setAttribute("user", account);
	} else {
	    session.removeAttribute("account");
	}
	session.setAttribute("success", "account.saveSuccess");
	LOGGER.debug("User " + account.getEmail() + " has been updated.");
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