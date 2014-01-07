<%@ tag language="java" pageEncoding="UTF-8"%>
<%@	attribute name="sidebar" required="true"%>
<%@	attribute name="title" required="true"%>
<%@ attribute name="body" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
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
	<div class="container showgrid">

		<div id="pageheader" class="span-24 last">
			<t:header />
		</div>
		<hr />
		<c:choose>
			<c:when test="${sidebar eq true}">
				<div id="pagesidebar" class="span-5 colborder">
					<t:sidebar />
				</div>
				<div id="pagebody" class="span-18 last">
					<jsp:doBody />
				</div>

			</c:when>
			<c:otherwise>
				<jsp:doBody />
			</c:otherwise>
		</c:choose>
		<hr />
		<div id="pagefooter" class="span-24 last">
			<h1>Footer</h1>
		</div>

	</div>
</body>
</html>
