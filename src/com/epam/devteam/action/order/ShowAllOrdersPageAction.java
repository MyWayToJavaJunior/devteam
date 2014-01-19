package com.epam.devteam.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.Order;

public class ShowAllOrdersPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowAllOrdersPageAction.class);
    private static ActionResult result = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	LOGGER.debug("Action start.");
	HttpSession session;
	DaoFactory factory;
	OrderDao dao;
	List<Order> orders = null;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taken.");
	    throw new ActionException();
	}
	dao = factory.getOrderDao();
	session = request.getSession();
	try {
	    orders = dao.list();
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be created.");
	    throw new ActionException();
	}
	session.setAttribute("orders", orders);
	result.setMethod(ActionResult.METHOD.FORWARD);
	result.setView("all-orders");
	return result;
    }

}
