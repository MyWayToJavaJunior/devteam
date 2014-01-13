<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:account />
<c:if test="${user.role == 'ADMINISTRATOR'}">
	<t:administrator-tools />
</c:if>
<c:if test="${user.role == 'CUSTOMER'}">
	<t:customer-tools />
</c:if>
<c:if test="${user.role == 'MANAGER'}">
	<t:manager-tools />
</c:if>

