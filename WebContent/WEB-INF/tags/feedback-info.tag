<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="feedback" required="true" type="com.epam.devteam.entity.feedback.Feedback"%>
<h1>
	<fmt:message bundle="${msg}" key="common.feedback" />
</h1>
<div class="span-18 last">
	<fieldset>
		<legend>
			<fmt:message bundle="${msg}" key="common.managerInfo" />
		</legend>
		<fmt:message bundle="${msg}" key="common.firstName" />: ${feedback.manager.firstName}<br />
		<fmt:message bundle="${msg}" key="common.lastName" />: ${feedback.manager.lastName}<br /> 
		<fmt:message bundle="${msg}" key="common.email" />: ${feedback.manager.email}<br />
		<fmt:message bundle="${msg}" key="common.phone" />: ${feedback.manager.phone}<br /> 
		<fmt:message bundle="${msg}" key="common.address" />: ${feedback.manager.address}<br />
	</fieldset>
</div>
<div class="span-18">
	<p>
		<label>
			<fmt:message bundle="${msg}" key="common.message" /><br />
		</label>
		<textarea class="span-18" disabled="disabled" style="resize: none">${feedback.message}</textarea>
	</p>
	<p>
		<label>
			<fmt:message bundle="${msg}" key="common.file" />
		</label> 
		<a href="do/download-file?source=feedback">${feedback.fileName}</a>
	</p>
</div>