<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="role" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:choose>
	<c:when test="${role == 'CUSTOMER'}">
		<fmt:message bundle="${msg}" key="common.role.customer" />
	</c:when>
	<c:when test="${role == 'DEVELOPER'}">
		<fmt:message bundle="${msg}" key="common.role.developer" />
	</c:when>
	<c:when test="${role == 'MANAGER'}">
		<fmt:message bundle="${msg}" key="common.role.manager" />
	</c:when>
	<c:when test="${role == 'ADMINISTRATOR'}">
		<fmt:message bundle="${msg}" key="common.role.administrator" />
	</c:when>
	<c:otherwise>${role}</c:otherwise>
</c:choose>

