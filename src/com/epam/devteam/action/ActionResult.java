package com.epam.devteam.action;

/**
 * The <code>ActionResult</code> represents a result of <code>Action</code>
 * class performing. It contains fields that are used by <code>Controller</code>
 * to perform method (Get or Post) and display view.
 * 
 * @date Jan 14, 2014
 * @author Andrey Kovalskiy
 * @see com.epam.devteam.servlet.Controller
 * @see com.epam.devteam.action.Action
 * 
 */
public class ActionResult {
    public enum METHOD {
	FORWARD, REDIRECT
    }

    private METHOD method;
    private String view;

    /**
     * Initializes a newly created {@code ActionResult} object.
     */
    public ActionResult() {
	super();
    }

    /**
     * Initializes a newly created {@code ActionResult} object with given
     * values.
     * 
     * @param method Method to perform
     * @param view View to show.
     */
    public ActionResult(METHOD method, String view) {
	super();
	this.method = method;
	this.view = view;
    }

    /**
     * Returns the method field value.
     * 
     * @return The method.
     */
    public METHOD getMethod() {
	return method;
    }

    /**
     * Sets the method field value.
     * 
     * @param method The method to set.
     */
    public void setMethod(METHOD method) {
	this.method = method;
    }

    /**
     * Returns the view field value.
     * 
     * @return The view.
     */
    public String getView() {
	return view;
    }

    /**
     * Sets the view field value.
     * 
     * @param view The view to set.
     */
    public void setView(String view) {
	this.view = view;
    }

}
