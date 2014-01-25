<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="order" required="true" type="com.epam.devteam.entity.order.Order"%>
<h1>
	<fmt:message bundle="${msg}" key="common.order" /> #${order.id}
</h1>
<h3>
	<fmt:message bundle="${msg}" key="common.date" /> : ${order.date} <br /> 
	<fmt:message bundle="${msg}" key="common.status" /> : 
		<t:order-status-info status="${order.status}"/><br />
	<c:if test="${order.status == 'ACCEPTED' or order.status == 'DENIED'}">
		<a href="do/feedback">
			<fmt:message bundle="${msg}" key="action.readFeedback" /> 
		</a>
	</c:if>
</h3>
<div class="span-18 last">
	<fieldset>
		<legend>
			<fmt:message bundle="${msg}" key="common.customerInfo" />
		</legend>
		<c:set var="isCustomer" value="${user.role == 'CUSTOMER'}" />
		<fmt:message bundle="${msg}" key="common.firstName" />:
			${isCustomer ? user.firstName : order.customer.firstName}<br /> 
		<fmt:message bundle="${msg}" key="common.lastName" />: 
			${isCustomer ? user.lastName : order.customer.lastName}<br />
		<fmt:message bundle="${msg}" key="common.company" />:
			${isCustomer ? user.company : order.customer.company}<br /> 
		<fmt:message bundle="${msg}" key="common.position" />: 
			${isCustomer ? user.position : order.customer.position}<br />
		<fmt:message bundle="${msg}" key="common.email" />:
			${isCustomer ? user.email : order.customer.email}<br /> 
		<fmt:message bundle="${msg}" key="common.phone" />: 
			${isCustomer ? user.phone : order.customer.phone}<br /> 
		<fmt:message bundle="${msg}" key="common.address" />: 
			${isCustomer ? user.address : order.customer.address}<br />
	</fieldset>
</div>
<div class="span-18">
	<p>
		<label>
			<fmt:message bundle="${msg}" key="common.subject" /><br />
		</label>
		<input class="span-18" type="text" disabled="disabled"
			value="<t:order-subject-info subject="${order.subject }"/>"/>
		</p>
	<p>
		<label>
			<fmt:message bundle="${msg}" key="common.topic" /><br />
		</label>
		<input class="span-18" type="text" disabled="disabled" value="${order.topic}" />
	</p>
	<p>
		<label>
			<fmt:message bundle="${msg}" key="common.message" /><br />
		</label>
		<textarea class="span-18" disabled="disabled" style="resize: none">${order.message}</textarea>
	</p>
	<c:if test="${not empty order.fileName}">
		<p>
			<label>
				<fmt:message bundle="${msg}" key="common.file" />:
			</label>
			<a href="do/download-file?source=order">${order.fileName}</a>
		</p>
	</c:if>
</div>