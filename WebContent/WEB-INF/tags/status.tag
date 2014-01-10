<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty error}">
	<font color="red"><%=session.getAttribute("error")%></font>
	<%
	    session.removeAttribute("error");
	%>
</c:if>
<c:if test="${not empty success}">
	<font color="blue"><%=session.getAttribute("success")%></font>
	<%
	    session.removeAttribute("success");
	%>
</c:if>