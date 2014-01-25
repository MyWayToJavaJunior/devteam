<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="true" title="Orders">
	<jsp:body>
		<div class="span-18 last">
			<h1><fmt:message bundle="${msg}" key="common.orders" /></h1>
			<table>
			<thead>
				<tr>
					<th>#</th>
					<th><fmt:message bundle="${msg}" key="common.topic" /></th>
					<th><fmt:message bundle="${msg}" key="common.status" /></th>
					<th><fmt:message bundle="${msg}" key="common.subject" /></th>
					<th><fmt:message bundle="${msg}" key="common.date" /></th>
					<th><fmt:message bundle="${msg}" key="common.tools" /></th>
				</tr>
			</thead>
				<c:if test="${not empty orders}">
					<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.id}</td>
							<td><a href="do/show-order?id=${order.id}">${order.topic}</a></td>
							<td><t:order-status-info status="${order.status}"/></td>
							<td><t:order-subject-info subject="${order.subject}"/></td>
							<td>${order.date}</td>
							<td>
								<c:if test="${order.status != 'ACCEPTED' and order.status != 'DENIED'}">
									<c:if test="${order.status != 'TERMINATED'}">
										<a href="do/edit-order?id=${order.id}">
											<img src="static/edit.png">
										</a>
										<a href="do/terminate-order?id=${order.id}">
											<img src="static/delete.png">
										</a>  
									</c:if>							
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</c:if>
			</table>
			<t:paging-controller action="do/customer-orders" />
		</div>
	</jsp:body>
</t:genericpage>