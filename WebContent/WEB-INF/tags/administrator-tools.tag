<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fieldset>
	<legend>
		<fmt:message bundle="${msg}" key="common.tools.administrator" />
	</legend>
	<form action="do/create-account" method="get">
		<button class="span-4">
			<fmt:message bundle="${msg}" key="action.createAccount" />
		</button>
	</form>
	<form action="do/manage-accounts" method="get">
		<input type="hidden" name="first-row" value="0"> 
		<input type="hidden" name="row-number" value="5">
		<button class="span-4">
			<fmt:message bundle="${msg}" key="action.editAccounts" />
		</button>
	</form>
</fieldset>

