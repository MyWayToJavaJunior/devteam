<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="true" title="Accounts management">
	<jsp:body>
		<c:if test="${not empty users}">
			<h1>Edit Users</h1>
			<table>
				<thead>
					<tr>
						<th>Id</th>
						<th>Email</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Role</th>
						<th>Registered</th>
						<th>Tools</th>
					</tr>
				</thead>
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
									src="static/edit.png"></a> 
									<c:if test="${user.role != 'ADMINISTRATOR'}">
									<c:choose>
									<c:when test="${user.active == true}">
										<a href="do/deactivate-account?id=${user.id}"><img
												src="static/delete.png"></a>
									</c:when>
									<c:otherwise>
										<a href="do/activate-account?id=${user.id}"><img
												src="static/activate.png"></a>
									</c:otherwise>
								</c:choose>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<t:paging-controller />
		</c:if>
	</jsp:body>
</t:genericpage>