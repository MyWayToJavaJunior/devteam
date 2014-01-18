<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fieldset>
	<legend>
		<fmt:message bundle="${msg}" key="common.tools.customer" />
	</legend>
	<form action="do/create-order" method="get">
		<button class="span-4">
			<fmt:message bundle="${msg}" key="action.createOrder" />
		</button>
	</form>
	<form action="do/customer-orders" method="get">
		<button class="span-4">
			<fmt:message bundle="${msg}" key="action.allOrders" />
		</button>
	</form>
</fieldset>