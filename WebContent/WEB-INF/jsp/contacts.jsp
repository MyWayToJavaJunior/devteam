<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:genericpage sidebar="true" title="Contacts">
	<jsp:body>
		<h3>
			<fmt:message bundle="${msg}" key="common.address" />: 7 Gogol str.  
		</h3>
		<h3>
			<fmt:message bundle="${msg}" key="common.phone" />: 8-7869-35-643-69
		</h3>
		<h3>
			<fmt:message bundle="${msg}" key="common.email" />: postus@devteam.com
		</h3>
	</jsp:body>
</t:genericpage>