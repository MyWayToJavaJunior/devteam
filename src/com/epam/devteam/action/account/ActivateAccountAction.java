package com.epam.devteam.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.Action;
import com.epam.devteam.action.ActionResult;
import com.epam.devteam.action.exception.ActionDatabaseFailException;
import com.epam.devteam.action.exception.ActionException;
import com.epam.devteam.dao.DaoException;
import com.epam.devteam.dao.DaoFactory;
import com.epam.devteam.dao.UserDao;
import com.epam.devteam.service.validation.RequestFieldsValidator;

public class ActivateAccountAction implements Action {
    private static final Logger LOGGER = Logger
	    .getLogger(ActivateAccountAction.class);
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
	String tempId = request.getParameter("id");
	boolean idFieldEqualsNull = false;
	boolean idFieldEmpty = false;
	int id = 0;
	idFieldEqualsNull = RequestFieldsValidator.equalsNull(tempId);
	if (idFieldEqualsNull) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.debug("Id form field equals null.");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	idFieldEmpty = RequestFieldsValidator.empty(tempId);
	if (idFieldEmpty) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.debug("Id form field value is empty.");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	try {
	    id = Integer.parseInt(tempId);
	} catch (IllegalArgumentException e) {
	    session.setAttribute("error", "error.badRequest");
	    LOGGER.debug("Id format is wrong.");
	    return new ActionResult(ActionResult.METHOD.FORWARD, "error");
	}
	try {
	    userDao().updateActiveStatus(id, true);
	    ;
	    LOGGER.warn("User has been deleted.");
	} catch (DaoException e) {
	    LOGGER.warn("User cannot be deleted.");
	    throw new ActionDatabaseFailException(e);
	}
	session.setAttribute("success", "account.activateSuccess");
	return new ActionResult(ActionResult.METHOD.FORWARD, "success");
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
