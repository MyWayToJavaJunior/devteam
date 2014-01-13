/**
 * 
 */
package com.epam.devteam.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.epam.devteam.entity.user.User;

/**
 * @date Jan 6, 2014 	
 * @author Andrey Kovalskiy
 *
 */
public class LogInfoTag extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(SigninTag.class);
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
	User user = (User) pageContext.getSession().getAttribute("user");
	String str;
	if (user == null) {
	    str = "Please authorise to get access to all features.";
	} else {
	    str = "<a href='main' style='color: #000000'>Welcome: " + user.getFirstName() + " " + user.getLastName() + "</a>";
	}
	try {
	    JspWriter out = pageContext.getOut();
	    out.print(str);
	} catch (IOException e) {
	    LOGGER.warn("Jsp write failed.");
	}
	return SKIP_BODY;
    }
}
