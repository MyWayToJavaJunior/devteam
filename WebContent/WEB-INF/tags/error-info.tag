<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="error" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty error}">
	<br />
	<font color="red"><fmt:message bundle="${msg}" key="${error}" /></font>
	<br />
</c:if>
