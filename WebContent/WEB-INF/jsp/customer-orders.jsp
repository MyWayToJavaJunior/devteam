<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="true" title="Orders">
	<jsp:body>
		<div class="span-18 last">
		<c:if test="${not empty orders}">
			<h1>Orders</h1>
			<table>
			<thead>
				<tr>
					<th>#</th>
					<th>Topic</th>
					<th>Status</th>
					<th>Subject</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="order" items="${orders}">
				<tr>
					<td>${order.id}</td>
					<td><a href="do/order?id=${order.id}">${order.topic}</a></td>
					<td>${order.status}</td>
					<td>${order.subject}</td>
					<td>${order.date}</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			</c:if>
			<c:if test="${empty orders}">
				<h1>There are no orders</h1>
			</c:if>	
		</div>
	</jsp:body>
</t:genericpage>