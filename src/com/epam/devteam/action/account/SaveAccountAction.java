package com.epam.devteam.action.account;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
import com.epam.devteam.util.validator.FieldType;
import com.epam.devteam.util.validator.RequestFieldsValidator;
import com.epam.devteam.util.validator.ValidationException;

/**
 * The <code>SaveAccountAction</code> is used to save account changes.
 * 
 * @date Jan 8, 2014
 * @author Andrey Kovalskiy
 */
public class SaveAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(SaveAccountAction.class);
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
	User accountBefore = (User) session.getAttribute("account");
	User accountAfter;
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
	String day = request.getParameter("day");
	String month = request.getParameter("month");
	String year = request.getParameter("year");
	Date date;
	int id = 0;
	boolean active = false;
	boolean fieldsEqualsNull = false;
	boolean fieldsEmpty = false;
	boolean fieldsLengthValid = false;
	boolean changed = false;
	fieldsEqualsNull = RequestFieldsValidator.equalNull(tempId, tempRole,
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
	    date = new Date(new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH)
		    .parse(year + "-" + month + "-" + day).getTime());
	} catch (ParseException e) {
	    LOGGER.warn("Birth date is wrong");
	    throw new ActionBadRequestException(e);
	}
	try {
	    switch (accountBefore.getRole()) {
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
	    accountAfter = customer;
	    break;
	default:
	    Employee employee = new Employee();
	    employee.setQualification(qualification);
	    accountAfter = employee;
	    break;
	}
	accountAfter.setEmail(accountBefore.getEmail());
	accountAfter.setRegistrationDate(accountBefore.getRegistrationDate());
	accountAfter.setId(id);
	accountAfter.setRole(role);
	accountAfter.setActive(active);
	accountAfter.setFirstName(firstName);
	accountAfter.setLastName(lastName);
	accountAfter.setBirthDate(date);
	accountAfter.setAddress(address);
	accountAfter.setPhone(phone);
	changed = isChanged(accountAfter, accountBefore);
	if (!changed) {
	    LOGGER.debug("There is no changes to save.");
	    session.setAttribute("accountEditError", "account.notChanged");
	    return new ActionResult(ActionResult.METHOD.REDIRECT,
		    request.getHeader("referer"));
	}
	LOGGER.debug("Account has been changed.");
	try {
	    userDao().update(accountAfter);
	    LOGGER.debug("User " + accountAfter.getEmail()
		    + " has been updated.");
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be updated.");
	    throw new ActionDatabaseFailException(e);
	}
	user = (User) session.getAttribute("user");
	if (user.getId() == accountAfter.getId()) {
	    LOGGER.debug("User's own account.");
	    accountAfter.setEmail(user.getEmail());
	    accountAfter.setRegistrationDate(user.getRegistrationDate());
	    session.setAttribute("user", accountAfter);
	}
	session.removeAttribute("account");
	session.removeAttribute("accountEditError");
	session.setAttribute("success", "account.saveSuccess");
	session.setAttribute("link",
		"do/edit-account?id=" + accountAfter.getId());
	return new ActionResult(ActionResult.METHOD.REDIRECT, "success");
    }

    /**
     * Is used to check whether account was modified.
     * 
     * @param accountAfter Account 
     * @param accountBefore
     * @return
     */
    private boolean isChanged(User accountAfter, User accountBefore) {
	Customer customerAfter;
	Customer customerBefore;
	Employee employeeAfter;
	Employee employeeBefore;
	if (accountAfter.getRole() != accountBefore.getRole()
		|| accountAfter.isActive() != accountBefore.isActive()
		|| !accountAfter.getFirstName().equals(
			accountBefore.getFirstName())
		|| !accountAfter.getLastName().equals(
			accountBefore.getLastName())
		|| !accountAfter.getBirthDate().equals(
			accountBefore.getBirthDate())
		|| !accountAfter.getAddress()
			.equals(accountBefore.getAddress())
		|| !accountAfter.getPhone().equals(accountBefore.getPhone())) {
	    return true;
	}
	switch (accountAfter.getRole()) {
	case CUSTOMER:
	    customerAfter = (Customer) accountAfter;
	    customerBefore = (Customer) accountBefore;
	    if (!customerAfter.getCompany().equals(customerBefore.getCompany())
		    || !customerAfter.getPosition().equals(
			    customerBefore.getPosition())) {
		return true;
	    }
	default:
	    employeeAfter = (Employee) accountAfter;
	    employeeBefore = (Employee) accountBefore;
	    if (!employeeAfter.getQualification().equals(
		    employeeBefore.getQualification())) {
		return true;
	    }

	}
	return false;

    }

    /**
     * Is used to get user dao. It initializes dao during the first use.
     * 
     * @return The user.
     * @throws DaoException If something fails.
     */
    private UserDao userDao() throws DaoException {
	if (dao == null) {
	    dao = DaoFactory.getDaoFactory().getUserDao();
	}
	return dao;
    }
}