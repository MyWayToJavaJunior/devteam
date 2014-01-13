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
public class SigninTag extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(SigninTag.class);
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
	User user = (User) pageContext.getSession().getAttribute("user");
	String str;
	if (user == null) {
	    str = "<a href='do/signin'>Sign in</a>";
	} else {
	    str = "<a href='do/signout'>Sign out</a>";
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
