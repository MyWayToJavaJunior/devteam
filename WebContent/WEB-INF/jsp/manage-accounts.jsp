<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="false" title="Accounts management">
	<jsp:body>
		<div class="span-7 colborder">
		<h1>Create User</h1>
		<t:account-create />
		</div>
		<div class="span-16 last">
		<c:if test="${not empty users}">
			<h1>Edit Users</h1>
			<form action="do/manage-account" method="post">
			<table>
				<thead>
					<tr>
						<th>Id</th>
						<th>Email</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Role</th>
						<th>Is Active</th>
						<th>Registration Date</th>
					</tr>
				</thead>
			</table>
			<div style="height: 300px; overflow: auto;">
				<table>
					<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td><input type="radio" name="account-id" value="${user.id}" />${user.id}</td>
							<td>${user.email}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.role}</td>
							<td>${user.active}</td>
							<td>${user.registrationDate}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<input type="submit" value="Edit user">
			</form>
		</c:if>	
		</div>
	</jsp:body>
</t:genericpage>