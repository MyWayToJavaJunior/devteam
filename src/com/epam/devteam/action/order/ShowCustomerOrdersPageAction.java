package com.epam.devteam.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.OrderDao;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.user.User;

public class ShowCustomerOrdersPageAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowCustomerOrdersPageAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	LOGGER.debug("Action start.");
	HttpSession session;
	DaoFactory factory;
	OrderDao dao;
	User user;
	List<Order> orders = null;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	dao = factory.getOrderDao();
	session = request.getSession();
	user = (User) session.getAttribute("user");
	try {
	    orders = dao.listByCustomerId(user.getId());
	} catch (DaoException e) {
	    LOGGER.warn("Order cannot be created.");
	    throw new ActionException();
	}
	session.setAttribute("orders", orders);
	return "customer-orders";
    }

}
