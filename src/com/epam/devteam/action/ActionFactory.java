/**
 * 
 */
package com.epam.devteam.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.devteam.action.account.ActivateAccountAction;
import com.epam.devteam.action.account.ChangeAccountPasswordAction;
import com.epam.devteam.action.account.CreateAccountAction;
import com.epam.devteam.action.account.DeactivateAccountAction;
import com.epam.devteam.action.account.SaveAccountAction;
import com.epam.devteam.action.account.ShowAccountsManagementPageAction;
import com.epam.devteam.action.account.ShowCreateAccountPageAction;
import com.epam.devteam.action.account.ShowEditAccountPageAction;
import com.epam.devteam.action.account.SigninAction;
import com.epam.devteam.action.account.SignoutAction;
import com.epam.devteam.action.feedback.CreateFeedbackAction;
import com.epam.devteam.action.general.DownloadFileAction;
import com.epam.devteam.action.general.SetLanguageAction;
import com.epam.devteam.action.general.ShowAboutUsPageAction;
import com.epam.devteam.action.general.ShowContactsPageAction;
import com.epam.devteam.action.general.ShowErrorPageAction;
import com.epam.devteam.action.general.ShowMainPageAction;
import com.epam.devteam.action.general.ShowSuccessPageAction;
import com.epam.devteam.action.order.CreateOrderAction;
import com.epam.devteam.action.order.SaveOrderAction;
import com.epam.devteam.action.order.ShowAllOrdersPageAction;
import com.epam.devteam.action.order.ShowCreateOrderPageAction;
import com.epam.devteam.action.order.ShowCustomerOrdersPageAction;
import com.epam.devteam.action.order.ShowEditOrderPageAction;
import com.epam.devteam.action.order.ShowOrderPageAction;
import com.epam.devteam.action.order.ShowProcessOrderPageAction;
import com.epam.devteam.action.order.TerminateOrderAction;

/**
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 */
public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static Map<String, Action> actions = createInitialMap();

    private static Map<String, Action> createInitialMap() {
	Map<String, Action> actions = new HashMap<String, Action>();
	actions.put("GET/", new ShowMainPageAction());
	actions.put("GET/*", new ShowMainPageAction());
	actions.put("GET/main", new ShowMainPageAction());
	actions.put("GET/contacts", new ShowContactsPageAction());
	actions.put("GET/about-us", new ShowAboutUsPageAction());
	actions.put("GET/error", new ShowErrorPageAction());
	actions.put("GET/success", new ShowSuccessPageAction());
	actions.put("POST/set-language", new SetLanguageAction());
	actions.put("GET/download-file", new DownloadFileAction());
	actions.put("POST/signin", new SigninAction());
	actions.put("GET/signout", new SignoutAction());
	actions.put("GET/create-account", new ShowCreateAccountPageAction());
	actions.put("POST/create-account", new CreateAccountAction());
	actions.put("GET/edit-account", new ShowEditAccountPageAction());
	actions.put("POST/save-account", new SaveAccountAction());
	actions.put("GET/manage-accounts",
		new ShowAccountsManagementPageAction());
	actions.put("GET/deactivate-account", new DeactivateAccountAction());
	actions.put("GET/activate-account", new ActivateAccountAction());
	actions.put("POST/change-password", new ChangeAccountPasswordAction());
	actions.put("GET/show-order", new ShowOrderPageAction());
	actions.put("GET/create-order", new ShowCreateOrderPageAction());
	actions.put("POST/create-order", new CreateOrderAction());
	actions.put("GET/customer-orders", new ShowCustomerOrdersPageAction());
	actions.put("GET/terminate-order", new TerminateOrderAction());
	actions.put("GET/edit-order", new ShowEditOrderPageAction());
	actions.put("POST/save-order", new SaveOrderAction());
	actions.put("GET/show-all-orders", new ShowAllOrdersPageAction());
	actions.put("GET/process-order", new ShowProcessOrderPageAction());
	actions.put("POST/create-feedback", new CreateFeedbackAction());
	return actions;
    }

    public static Action getAction(HttpServletRequest request) {
	HttpSession session;
	String req = request.getMethod() + request.getPathInfo();
	Action action = actions.get(req);
	if (action == null) {
	    action = actions.get("GET/error");
	    session = request.getSession();
	    session.setAttribute("error", "error.notFound");
	    LOGGER.debug("Unknown request.");
	    return action;
	}
	return action;
    }
}
