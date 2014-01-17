<%@ tag language="java" pageEncoding="UTF-8"%>
<fieldset>
	<legend>Administration tools</legend>
	<form action="do/create-account" method="get">
		<button class="span-4">Create account</button>
	</form>
	<form action="do/manage-accounts" method="get">
		<input type="hidden" name="first-row" value="0"> <input
			type="hidden" name="row-number" value="5">
		<button class="span-4">Edit user accounts</button>
	</form>
</fieldset>

