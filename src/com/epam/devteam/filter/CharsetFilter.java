package com.epam.devteam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * The <code>CharsetFilter</code> is used to set UTF-8 encoding for request.
 * 
 * @date Jan 22, 2014
 * @author Andrey Kovalskiy
 * 
 */
public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	request.setCharacterEncoding("UTF-8");
	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}