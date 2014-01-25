<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<t:genericpage sidebar="true" title="Order">
	<jsp:body>
		<t:order-info order="${order}"/>		
		<h1>
			<fmt:message bundle="${msg}" key="common.feedback" />
		</h1>
		<form action="do/create-feedback" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="order-id" value="${order.id}" />
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.orderNewStatus" />
						<font color="red">*</font><br />
				</label>
				<select class="span-18" name="order-status">
					<option value="ACCEPTED" selected>
						<fmt:message bundle="${msg}" key="order.status.accepted" />
					</option>
					<option value="DENIED">
						<fmt:message bundle="${msg}" key="order.status.denied" />
					</option>
				</select>
			</p>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.message" />
						<font color="red">*</font><br />
				</label>
				<t:error-info error="${messageError}" />
				<textarea class="span-18" name="message" style="resize: none"
					maxlength="2000"></textarea>
			</p>
			<p>
				<label>
					<fmt:message bundle="${msg}" key="common.attachFile" /><br />
				</label>
				<img src="static/trash-delete.png" onclick="clearFileInput()">
				<input id="file" type="file" name="file" />
			</p>
			<button class="span-18" type="submit">
				<fmt:message bundle="${msg}" key="action.submit" />
			</button>
		</form>
	</jsp:body>
</t:genericpage>

