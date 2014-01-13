<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:genericpage sidebar="true" title="Order">
	<jsp:body>
	<h1>Order #${order.id} </h1>
	<h3>date: ${order.date} <br /> 
		status: ${order.status}<br />
		<c:if test="${order.status == 'ACCEPTED' or order.status == 'DENIED'}">
			<a href="do/feedback">Read feedback</a>
		</c:if>
		</h3>
	<t:status />
	<div class="span-18 last">
		<fieldset>
			<legend>Customer Info</legend>
			<c:set var="isCustomer" value="${user.role == 'CUSTOMER'}" />
			First Name: ${isCustomer ? user.firstName : order.customer.firstName}<br /> 
			Last Name: ${isCustomer ? user.lastName : order.customer.lastName}<br />
			Company: ${isCustomer ? user.company : order.customer.company}<br /> 
			Position: ${isCustomer ? user.position : order.customer.position}<br />
			Email: ${isCustomer ? user.email : order.customer.email}<br /> 
			Phone: ${isCustomer ? user.phone : order.customer.phone}<br /> 
			Address: ${isCustomer ? user.address : order.customer.address}<br />
		</fieldset>
	</div>
	<div class="span-18">
			<p>
				<label>Subject<br /></label>
				<input class="span-18" type="text" disabled="disabled"
					value="${order.subject}" />
			</p>
			<p>
				<label>Topic<br /></label>
				<input class="span-18" type="text" disabled="disabled"
					value="${order.topic}" />
			</p>
			<p>
				<label>Message<br /></label>
				<textarea class="span-18" disabled="disabled" style="resize: none">${order.message}</textarea>
			</p>
			<p>
				<label>Attached file:</label>
				<a href="do/download-file?source=feedback">${order.fileName}</a>
			</p>
		</div>
		<c:if
			test="${order.status == 'PENDING' or order.status == 'TERMINATED'}">
			<c:choose>
				<c:when test="${isCustomer}">
					<div class="span-9">
						<form action="do/edit-order" method="get">
							<input type="hidden" name="id" value="${order.id}">
							<button class="span-9">Edit order</button>
						</form>
					</div>
					<div class="span-9 last">
						<form action="do/terminate-order" method="get">
							<input type="hidden" name="id" value="${order.id}">
							<button class="span-9">Terminate order</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<form action="do/process-order?id=${order.id}" method="get">
						<input type="hidden" name="id" value="${order.id}">
						<button class="span-18">Process order</button>
					</form>
				</c:otherwise>
			</c:choose>
		</c:if>
	</jsp:body>
</t:genericpage>

