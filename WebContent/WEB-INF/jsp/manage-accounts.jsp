<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:genericpage sidebar="true" title="Accounts management">
	<jsp:body>
		<h1><fmt:message bundle="${msg}" key="common.accounts" /></h1>
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message bundle="${msg}" key="common.email" /></th>
					<th><fmt:message bundle="${msg}" key="common.firstName" /></th>
					<th><fmt:message bundle="${msg}" key="common.lastName" /></th>
					<th><fmt:message bundle="${msg}" key="common.role" /></th>
					<th><fmt:message bundle="${msg}" key="common.registered" /></th>
					<th><fmt:message bundle="${msg}" key="common.tools" /></th>
				</tr>
			</thead>
			<c:if test="${not empty users}">
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.email}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td><t:role-info role="${user.role}" /></td>
							<td>${user.registrationDate}</td>
							<td><a href="do/edit-account?id=${user.id}"><img
									src="static/edit.png"></a> <c:if
									test="${user.role != 'ADMINISTRATOR'}">
									<c:choose>
										<c:when test="${user.active == true}">
											<a href="do/deactivate-account?id=${user.id}">
												<img src="static/delete.png">
											</a>
										</c:when>
										<c:otherwise>
											<a href="do/activate-account?id=${user.id}">
											<img src="static/activate.png">
										</a>
										</c:otherwise>
									</c:choose>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
		<t:paging-controller />
	</jsp:body>
</t:genericpage>