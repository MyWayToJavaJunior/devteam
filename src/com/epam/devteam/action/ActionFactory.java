/**
 * 
 */
package com.epam.devteam.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.devteam.action.account.CreateAccountAction;
import com.epam.devteam.action.account.ShowAccountPageAction;
import com.epam.devteam.action.account.SigninAction;
import com.epam.devteam.action.account.SignoutAction;
import com.epam.devteam.action.account.UpdateAccountAction;

/**
 * @date Jan 5, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static Map<String, Action> actions = createInitialMap();

    private static Map<String, Action> createInitialMap(){
	Map<String, Action> actions = new HashMap<String, Action>();
	actions.put("GET/", new ShowMainPageAction());
	actions.put("GET/main", new ShowMainPageAction());
	actions.put("GET/error", new ShowErrorPageAction());
	actions.put("POST/signin", new SigninAction());
	actions.put("GET/signout", new SignoutAction());
	actions.put("GET/account", new ShowAccountPageAction());
	actions.put("POST/create-account", new CreateAccountAction());
	actions.put("POST/modify-account", new UpdateAccountAction());
	return actions;
    }

    public static Action getAction(HttpServletRequest request) {
	String req = request.getMethod() + request.getPathInfo();
	System.out.println(req);
	Action action = actions.get(req);
	if (action == null) {
	    action = actions.get("GET/error");
	    LOGGER.warn("Unknown request was detected:" + req);
	}
	return action;
    }
}
