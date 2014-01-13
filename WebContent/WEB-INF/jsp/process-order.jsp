<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:genericpage sidebar="true" title="Order">
	<jsp:body>
		<t:status />
		<h1>Response for Order #${order.id} </h1>
		<h3>Topic: ${order.topic}<br />
			Date: ${order.date}<br />
		</h3>
		<form action="do/create-feedback" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="order-id" value="${order.id}" />
			<p>
				<label>New order status</label>
				<select class="span-18" name="status">
					<option value="ACCEPTED" selected>Accept</option>
					<option value="DENIED">Deny</option>
				</select>
			</p>
			<p>
				<label>Message (up to 1000 symbols)<font color="red">*</font><br /></label>
				<textarea class="span-18" name="message" style="resize: none"
					maxlength="1000"></textarea>
			</p>
			<p>
			<label>Attach file (up to 10MB)<br /></label>
			<input type="file" name="file" />
			</p>
			<button class="span-18" type="submit">Submit</button>
		</form>
	</jsp:body>
</t:genericpage>

