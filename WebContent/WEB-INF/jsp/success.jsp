<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="false" title="Success">
	<jsp:body>
		<div class="span-14 prepend-5" align="center">
			<h2>
				<font color="blue"><fmt:message bundle="${msg}"
						key="${success}" /></font>
				<c:if test="${not empty link}">
					<br />
					<a href="${link}">Click here</a>
				</c:if> 
			</h2>
		</div>
	</jsp:body>
</t:genericpage>