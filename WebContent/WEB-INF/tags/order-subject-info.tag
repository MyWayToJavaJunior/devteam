<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="subject" required="true" %>
<c:if test="${subject == 'OTHER'}">
<fmt:message bundle="${msg}" key="common.other" />
</c:if>
<c:if test="${subject == 'APPLICATION_DEVELOPMENT'}">
<fmt:message bundle="${msg}" key="common.applicationDevelopment" />
</c:if>
<c:if test="${subject == 'APPLICATION_TESTING'}">
<fmt:message bundle="${msg}" key="common.applicationTesting" />
</c:if>
<c:if test="${subject == 'APPLICATION_SUPPORT'}">
<fmt:message bundle="${msg}" key="common.applicationSupport" />
</c:if>