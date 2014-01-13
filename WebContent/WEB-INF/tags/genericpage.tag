<%@ tag language="java" pageEncoding="UTF-8"%>
<%@	attribute name="sidebar" required="true"%>
<%@	attribute name="title" required="true"%>
<%@ attribute name="body" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" scope="session" />
<html>
<head>
<title>${title}</title>
<base href="${pageContext.request.contextPath}/">
<link rel="stylesheet" type="text/css" href="css/screen.css"
	media="screen, projection">
<link rel="stylesheet" type="text/css" href="css/print.css"
	media="print">
</head>
<body>
	<div class="container">

		<div id="header" class="banner span-24 last">
			<t:header />
		</div>
		<hr />
		<c:choose>
			<c:when test="${sidebar eq true}">
				<div id="sidebar" class="span-5 colborder">
					<t:sidebar />
				</div>
				<div id="body" class="span-18 last">
					<jsp:doBody />
				</div>
			</c:when>
			<c:otherwise>
				<jsp:doBody />
			</c:otherwise>
		</c:choose>
		<hr />
		<div id="footer" class="banner span-24 last">
			<t:footer />
		</div>

	</div>
</body>
</html>
