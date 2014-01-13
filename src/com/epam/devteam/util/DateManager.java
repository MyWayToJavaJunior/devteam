package com.epam.devteam.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class DateManager {

    private DateManager() {

    }

    public static Date dateFromRequest(HttpServletRequest request) {
	Date date;
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	if (year == null || month == null || day == null) {
	    return null;
	}
	try {
	    java.util.Date tempDate = new SimpleDateFormat("yyyy-MM-d",
		    Locale.ENGLISH).parse(year + "-" + month + "-" + day);
	    date = new Date(tempDate.getTime());
	} catch (ParseException e) {
	    return null;
	}
	System.out.println(date);
	return date;
    }
}
