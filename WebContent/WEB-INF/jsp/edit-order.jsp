<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
	function clearFileInput() {
		var oldInput = document.getElementById("file");
		var newInput = document.createElement("input");
		newInput.type = "file";
		newInput.id = oldInput.id;
		newInput.name = oldInput.name;
		newInput.className = oldInput.className;
		newInput.style.cssText = oldInput.style.cssText;
		oldInput.parentNode.replaceChild(newInput, oldInput);
	}
</script>
<t:genericpage sidebar="true" title="Edit Order">
	<jsp:body>
	<h1>
		<fmt:message bundle="${msg}" key="action.editOrder" /> #${order.id}
	</h1>
	<h3>
		<fmt:message bundle="${msg}" key="common.date" /> : ${order.date} <br /> 
		<fmt:message bundle="${msg}" key="common.status" /> : 
			<t:order-status-info status="${order.status}"/>
	</h3>
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
		<form action="do/save-order" method="post"
				enctype="multipart/form-data">
			<input type="hidden" name="id" value="${order.id}">
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.subject" /><br />
				</label>
				<select class="span-18" name="subject">
					<option value="OTHER" ${order.subject == 'OTHER' ? 'selected' : ''}>
						<fmt:message bundle="${msg}" key="common.other" />
					</option>
					<option value="APPLICATION_DEVELOPMENT" 
						${order.subject == 'APPLICATION_DEVELOPMENT' ? 'selected' : ''}>
							<fmt:message bundle="${msg}" key="common.applicationDevelopment" />
					</option>
					<option value="APPLICATION_TESTING" 
						${order.subject == 'APPLICATION_TESTING' ? 'selected' : ''}>
							<fmt:message bundle="${msg}" key="common.applicationTesting" />
					</option>
					<option value="APPLICATION_SUPPORT" 
						${order.subject == 'APPLICATION_SUPPORT' ? 'selected' : ''}>
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
				<input class="span-18" type="text" name="topic" value="${order.topic}"
						maxlength="256" />
			</p>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.message" /><font
						color="red">*</font><br />
				</label>
				<t:error-info error="${messageError}" />
				<textarea class="span-18" name="message" style="resize: none"
						maxlength="2000">${order.message}</textarea>
			</p>
			<c:if test="${not empty order.fileName}">
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.file" />:
					</label>
					<a href="do/download-file?source=order">${order.fileName}</a>
				</p>
			</c:if>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.attachFile" /><br />
				</label>
				<img src="static/trash-delete.png" onclick="clearFileInput()">
				<input id="file" type="file" name="file" onchange="AlertFilesize();" /><br />
			</p>
			<button class="span-18" type="submit">
				<fmt:message bundle="${msg}" key="action.saveOrder" />
			</button>
			
		</form>
	</div>
	</jsp:body>
</t:genericpage>
