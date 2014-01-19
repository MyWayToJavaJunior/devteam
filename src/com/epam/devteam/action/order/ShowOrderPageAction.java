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
import com.epam.devteam.dao.FeedbackDao;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.feedback.Feedback;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.order.OrderStatus;
import com.epam.devteam.entity.user.User;
import com.epam.devteam.entity.user.UserRole;

/**
 * The <code>ShowOrderPageAction</code> is used to show page with required id.
 * Any customer can't watch other customers' orders. Managers can watch every
 * order.
 * 
 * @date Jan 19, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ShowOrderPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowOrderPageAction.class);
    private ActionResult result = new ActionResult();
    private OrderDao orderDao;
    private FeedbackDao feedbackDao;

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
	LOGGER.debug("Show order page action...");
	HttpSession session;
	User user;
	Order order;
	OrderStatus status;
	Feedback feedback;
	int id;
	try {
	    id = Integer.valueOf(request.getParameter("id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Order id is not valid.");
	    throw new ActionBadRequestException(e);
	}
	try {
	    order = orderDao().find(id);
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be fetched from database.");
	    throw new ActionBadRequestException(e);
	}
	session = request.getSession();
	if (order == null) {
	    LOGGER.debug("Order with required id is absent.");
	    session.setAttribute("error", "error.badRequest");
	    result.setMethod(ActionResult.METHOD.FORWARD);
	    result.setView("error");
	    return result;
	}
	user = (User) session.getAttribute("user");
	if (user.getRole().equals(UserRole.CUSTOMER)) {
	    if (order.getCustomer().getId() != user.getId()) {
		LOGGER.debug("Order cannot be shown: access denied.");
		session.setAttribute("error", "error.badRequest");
		result.setMethod(ActionResult.METHOD.FORWARD);
		result.setView("error");
		return result;
	    }
	}
	status = order.getStatus();
	if (status.equals(OrderStatus.ACCEPTED)
		|| status.equals(OrderStatus.DENIED)) {
	    try {
		feedback = feedbackDao().findByOrderId(id);
		session.setAttribute("feedback", feedback);
	    } catch (DaoException e) {
		LOGGER.warn("Feedback cannot be fetched from database.");
	    }
	} else {
	    session.removeAttribute("feedback");
	}
	session.setAttribute("order", order);
	result.setMethod(ActionResult.METHOD.FORWARD);
	result.setView("order");
	return result;
    }

    /**
     * Is used to get order dao. It initializes dao during the first use.
     * 
     * @return The order dao.
     * @throws DaoException If something fails.
     */
    private OrderDao orderDao() throws DaoException {
	if (orderDao == null) {
	    orderDao = DaoFactory.getDaoFactory().getOrderDao();
	}
	return orderDao;
    }

    /**
     * Is used to get feedback dao. It initializes dao during the first use.
     * 
     * @return The feedback dao.
     * @throws DaoException If something fails.
     */
    private FeedbackDao feedbackDao() throws DaoException {
	if (feedbackDao == null) {
	    feedbackDao = DaoFactory.getDaoFactory().getFeedbackDao();
	}
	return feedbackDao;
    }
}
