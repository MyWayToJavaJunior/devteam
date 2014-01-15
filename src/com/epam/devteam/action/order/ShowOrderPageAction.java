package com.epam.devteam.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.entity.order.Order;

public class ShowOrderPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowOrderPageAction.class);

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	LOGGER.debug("Show order page action.");
	HttpSession session;
	List<Order> orders;
	Order order = null;
	int id;
	try {
	    id = Integer.valueOf(request.getParameter("id"));
	} catch (IllegalArgumentException e) {
	    LOGGER.warn("Order id value is not defined or not correct.");
	    throw new ActionException();
	}
	session = request.getSession();
	orders = (List<Order>) session.getAttribute("orders");
	for (Order tempOrder : orders) {
	    if (tempOrder.getId().equals(id)) {
		order = tempOrder;
		break;
	    }
	}
	if (order == null) {
	    session.setAttribute("error", "action.resourceNotFound");
	    return "error";
	}
	session.setAttribute("order", order);
	return "order";
    }
}
