<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="status" required="true" %>
<c:if test="${status == 'PENDING'}">
	<fmt:message bundle="${msg}" key="order.status.pending" />
</c:if>
<c:if test="${status == 'TERMINATED'}">
	<fmt:message bundle="${msg}" key="order.status.terminated" />
</c:if>
<c:if test="${status == 'ACCEPTED'}">
	<font color="green">
		<b>
			<fmt:message bundle="${msg}" key="order.status.accepted" />
		</b>
	</font>
</c:if>
<c:if test="${status == 'DENIED'}">
	<font color="red">
		<b>
			<fmt:message bundle="${msg}" key="order.status.denied" />
		</b>
	</font>
</c:if>