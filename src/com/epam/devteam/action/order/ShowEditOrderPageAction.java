package com.epam.devteam.action.order;

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

/**
 * The <code>ShowEditOrderPageAction</code> is used to prepare and show page
 * where customer can edit his order.
 * 
 * @date Jan 19, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowEditOrderPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowEditOrderPageAction.class);
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
	HttpSession session;
	User user;
	Order order;
	int id;
	LOGGER.debug("Show order page action...");
	try {
	    id = Integer.valueOf(request.getParameter("id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Order id is not valid.");
	    throw new ActionBadRequestException(e);
	}
	session = request.getSession();
	order = (Order) session.getAttribute("order");
	user = (User) session.getAttribute("user");
	if ((order != null) && (order.getId() == id)) {
	    if (order.getCustomer().getId() == user.getId()) {
		LOGGER.debug("Order from session.");
		result.setMethod(ActionResult.METHOD.FORWARD);
		result.setView("edit-order");
		return result;
	    } else {
		LOGGER.debug("Order cannot be edited: access denied.");
		session.setAttribute("error", "error.badRequest");
		result.setMethod(ActionResult.METHOD.FORWARD);
		result.setView("error");
		return result;
	    }
	}
	try {
	    LOGGER.debug("Order from database.");
	    order = orderDao().find(id);
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be fetched from database.");
	    throw new ActionBadRequestException(e);
	}
	if (order == null) {
	    LOGGER.debug("Order with required id is absent.");
	    session.setAttribute("error", "error.badRequest");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	if (order.getCustomer().getId() != user.getId()) {
	    LOGGER.debug("Order cannot be edited: access denied.");
	    session.setAttribute("error", "error.badRequest");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	session.setAttribute("order", order);
	result.setMethod(ActionResult.METHOD.FORWARD);
	result.setView("edit-order");
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
