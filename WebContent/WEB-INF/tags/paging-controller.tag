<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="action" required="true"%>
<c:set var="firstRow" value="${param['first-row']}" />
<c:set var="rowNumber" value="${param['row-number']}" />
<c:set var="nextFirstRow" value="${firstRow+rowNumber}" />
<c:set var="previousFirstRow"
	value="${(firstRow-rowNumber > 0) ? firstRow-rowNumber : 0}" />
<div class="span-3">
	<form action="${action}" method="get">
		<input type="hidden" name="first-row" value="${previousFirstRow}">
		<input type="hidden" name="row-number" value="${rowNumber}">
		<button class="span-3">
			<fmt:message bundle="${msg}" key="tag.paging.previous" />
		</button>
	</form>
	<form action="${action}" method="get">
		<input type="hidden" name="first-row" value="0"> <select
			name="row-number" onchange="submit()">
			<option value="5" ${rowNumber==5 ? 'selected' : ''}>5</option>
			<option value="10" ${rowNumber==10 ? 'selected' : ''}>10</option>
			<option value="25" ${rowNumber==25 ? 'selected' : ''}>25</option>
			<option value="50" ${rowNumber==50 ? 'selected' : ''}>50</option>
		</select>
	</form>
</div>
<form action="${action}" method="get">
	<input type="hidden" name="first-row" value="${nextFirstRow}">
	<input type="hidden" name="row-number" value="${rowNumber}">
	<button class="span-3">
		<fmt:message bundle="${msg}" key="tag.paging.next" />
	</button>
</form>

