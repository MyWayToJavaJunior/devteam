<%@tag import="java.text.AttributedCharacterIterator.Attribute"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="date" required="true" type="java.sql.Date"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="year" value="${date.year+1900}"/>
<c:set var="month" value="${date.month + 1}" />
<c:set var="day" value="${date.date}" />
<select name="day">
	<c:forEach begin="1" end="31" var="i">
		<option value="${i}" ${day == i or empty day ? 'selected' : ''}>${i}</option>
	</c:forEach>
</select>
<select name="month">
	<option value="1" ${month == 1 or empty month ? 'selected' : ''}>January</option>
	<option value="2" ${month == 2 ? 'selected' : ''}>February</option>
	<option value="3" ${month == 3 ? 'selected' : ''}>March</option>
	<option value="4" ${month == 4 ? 'selected' : ''}>April</option>
	<option value="5" ${month == 5 ? 'selected' : ''}>May</option>
	<option value="6" ${month == 6 ? 'selected' : ''}>June</option>
	<option value="7" ${month == 7 ? 'selected' : ''}>July</option>
	<option value="8" ${month == 8 ? 'selected' : ''}>August</option>
	<option value="9" ${month == 9 ? 'selected' : ''}>September</option>
	<option value="10" ${month == 10 ? 'selected' : ''}>October</option>
	<option value="11" ${month == 11 ? 'selected' : ''}>November</option>
	<option value="12" ${month == 12 ? 'selected' : ''}>December</option>
</select>
<select name="year">
	<c:forEach begin="0" end="100" var="i">
		<option value="${2014-i}" ${year == 2014-i or empty year ? 'selected' : ''}>${2014-i}</option>
	</c:forEach>
</select>