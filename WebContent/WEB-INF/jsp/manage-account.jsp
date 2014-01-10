<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="false" title="Account management">
	<jsp:body>
		<div class="prepend-5">
			<t:account-edit localAccount="${accountToManage}" />
		</div>
	</jsp:body>
</t:genericpage>