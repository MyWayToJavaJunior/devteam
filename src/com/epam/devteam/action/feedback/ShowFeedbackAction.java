package com.epam.devteam.action.feedback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.FeedbackDao;
import com.epam.devteam.entity.order.Order;
import com.epam.devteam.entity.response.Feedback;

public class ShowFeedbackAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ShowFeedbackAction.class);

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ActionException {
	HttpSession session;
	DaoFactory factory;
	FeedbackDao dao;
	Feedback feedback;
	Order order;
	try {
	    factory = DaoFactory.getDaoFactory();
	} catch (DaoException e) {
	    LOGGER.warn("Dao factory cannot be taked.");
	    throw new ActionException();
	}
	dao = factory.getFeedbackDao();
	session = request.getSession();
	order = (Order) session.getAttribute("order");
	try {
	    feedback = dao.findByOrderId(order.getId());
	} catch (DaoException e) {
	    LOGGER.warn("Feedback cannot be found.");
	    throw new ActionException();
	}
	session.setAttribute("feedback", feedback);
	return "feedback";
    }

}
