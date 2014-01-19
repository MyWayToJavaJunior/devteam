<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fieldset>
	<legend>
		<fmt:message bundle="${msg}" key="common.tools.manager" />
	</legend>
	<form action="do/show-all-orders" method="get">
		<input type="hidden" name="first-row" value="0"> 
		<input type="hidden" name="row-number" value="5">
		<button class="span-4">
			<fmt:message bundle="${msg}" key="action.allOrders" />
		</button>
	</form>
</fieldset>