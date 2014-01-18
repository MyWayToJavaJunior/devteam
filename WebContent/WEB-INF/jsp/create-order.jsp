<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="true" title="Order">
	<jsp:body>
	<h1>
			<fmt:message bundle="${msg}" key="common.newOrder" />
	</h1>
	<div class="span-18 last">
		<fieldset>
			<legend>
					<fmt:message bundle="${msg}" key="common.customerInfo" />
			</legend>
			<fmt:message bundle="${msg}" key="common.firstName" />: ${user.firstName}<br /> 
			<fmt:message bundle="${msg}" key="common.lastName" />: ${user.lastName}<br />
			<fmt:message bundle="${msg}" key="common.company" />: ${user.company}<br /> 
			<fmt:message bundle="${msg}" key="common.position" />: ${user.position}<br />
			<fmt:message bundle="${msg}" key="common.email" />: ${user.email}<br /> 
			<fmt:message bundle="${msg}" key="common.phone" />: ${user.phone}<br /> 
			<fmt:message bundle="${msg}" key="common.address" />: ${user.address}<br />
		</fieldset>
	</div>
	<div class="span-18">
		<form action="do/create-order" method="post"
				enctype="multipart/form-data">
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.subject" /><br />
				</label>
				<select class="span-18" name="subject">
					<option value="OTHER" selected>
						<fmt:message bundle="${msg}" key="common.other" />
					</option>
					<option value="APPLICATION_DEVELOPMENT">
						<fmt:message bundle="${msg}" key="common.applicationDevelopment" />
					</option>
					<option value="APPLICATION_TESTING">
						<fmt:message bundle="${msg}" key="common.applicationTesting" />
					</option>
					<option value="APPLICATION_SUPPORT">
						<fmt:message bundle="${msg}" key="common.applicationSupport" />
					</option>
				</select>
			</p>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.topic" /><font
						color="red">*</font>
				</label>
				<t:error-info error="${topicError}" />
				<input class="span-18" type="text" name="topic" value="${topic}"/>
			</p>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.message" /><font
						color="red">*</font><br />
				</label>
				<t:error-info error="${messageError}" />
				<textarea class="span-18" name="message" style="resize: none" maxlength="2000">${message}</textarea>
			</p>
			<p>
			<label>
				<fmt:message bundle="${msg}" key="common.attachFile" /><br />
			</label>
			<input type="file" name="file" />
			</p>
			<button class="span-18" type="submit">
				<fmt:message bundle="${msg}" key="action.createOrder" />
			</button>
		</form>
	</div>
	</jsp:body>
</t:genericpage>

