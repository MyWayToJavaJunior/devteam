package com.epam.devteam.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionBadRequestException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.service.validation.RequestFieldsValidator;

/**
 * The <code>ShowCustomerOrdersPageAction</code> is used to show page where user
 * can check all orders created by him.
 * 
 * @date Jan 19, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowCustomerOrdersPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowCustomerOrdersPageAction.class);
    private ActionResult result = new ActionResult();
    private DaoFactory factory;
    private OrderDao dao;

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
	LOGGER.debug("Show customer's orders action...");
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	List<Order> orders = null;
	String tempFirstRow = (String) request.getParameter("first-row");
	String tempRowNumber = (String) request.getParameter("row-number");
	int firstRow = 0;
	int rowNumber = 0;
	if (RequestFieldsValidator.equalNull(tempFirstRow, tempRowNumber)
		|| RequestFieldsValidator.empty(tempFirstRow, tempRowNumber)) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Form fields are not valid: equal null");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	try {
	    firstRow = Integer.parseInt(tempFirstRow);
	    rowNumber = Integer.parseInt(tempRowNumber);
	} catch (IllegalArgumentException e) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Form fields are not valid: not a number");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	if ((rowNumber % 5 != 0) || (rowNumber > 50)) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.warn("Row number is not valid");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	try {
	    orders = orderDao().listByCustomerId(user.getId(), firstRow,
		    rowNumber);
	} catch (DaoException e) {
	    LOGGER.warn("Orders cannot be fetched from database.");
	    throw new ActionBadRequestException(e);
	}
	session.setAttribute("orders", orders);
	result.setMethod(ActionResult.METHOD.FORWARD);
	result.setView("customer-orders");
	return result;
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
     * Is used to get order dao. It initializes dao during the first use.
     * 
     * @return The order dao.
     * @throws DaoException If something fails.
     */
    private OrderDao orderDao() throws DaoException {
	if (dao == null) {
	    dao = daoFactory().getOrderDao();
	}
	return dao;
    }
}
