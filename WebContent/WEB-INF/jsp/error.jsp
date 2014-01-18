<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="false" title="Error">
	<jsp:body>
		<div class="span-14 prepend-5" align="center">
			<h2>
				<font color="red"><fmt:message bundle="${msg}" key="${error}" /></font>
			</h2>
			<img src="static/error.png"/>
		</div>
	</jsp:body>
</t:genericpage>