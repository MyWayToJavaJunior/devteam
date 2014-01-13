<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:genericpage sidebar="true" title="Feedback">
	<jsp:body>
	<h1>Feedback for order #${feedback.orderId} </h1>
	<h3>date: ${order.date}</h3>
	<t:status />
	<div class="span-18 last">
		<fieldset>
			<legend>Manager Info</legend>
			First Name: ${feedback.manager.firstName}<br /> 
			Last Name: ${feedback.manager.lastName}<br />
			Email: ${feedback.manager.email}<br /> 
			Phone: ${feedback.manager.phone}<br /> 
			Address: ${feedback.manager.address}<br />
		</fieldset>
	</div>
	<div class="span-18">
			<p>
				<label>Message<br /></label>
				<textarea class="span-18" disabled="disabled" style="resize: none">${feedback.message}</textarea>
			</p>
			<p>
				<label>Attached file:</label>
				<a href="do/download-file?source=feedback">${feedback.fileName}</a>
			</p>
		</div>
	</jsp:body>
</t:genericpage>
