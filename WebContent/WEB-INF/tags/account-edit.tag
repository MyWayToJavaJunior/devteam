<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="span-14 ">
	<form action="do/save-account" method="post">
		<input type="hidden" name="id" value="${account.id}">
		<fieldset>
			<p>
				<label><fmt:message bundle="${msg}" key="common.email" /></label><br />
				<input class="span-13" type="text" name="email" disabled="disabled"
					value="${account.email}" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.firstName" /></label><br />
				<input class="span-13" type="text" name="first-name"
					value="${account.firstName}" title="Enter email" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.lastName" /></label><br />
				<input class="span-13" type="text" name="last-name"
					value="${account.lastName}" />
			</p>
		</fieldset>
		<c:choose>
			<c:when test="${account.role == 'CUSTOMER'}">
				<fieldset>
					<p>
						<label><fmt:message bundle="${msg}" key="common.company" /></label><br />
						<input class="span-13" type="text" name="company"
							value="${account.company}" />
					</p>
					<p>
						<label><fmt:message bundle="${msg}" key="common.position" /></label><br />
						<input class="span-13" type="text" name="position"
							value="${account.position}" />
					</p>
				</fieldset>
			</c:when>
			<c:otherwise>
				<fieldset>
					<p>
						<label><fmt:message bundle="${msg}" key="common.birthDate" /></label><br />
						<t:date />
					</p>
					<p>
						<label><fmt:message bundle="${msg}"
								key="common.qualification" /></label><br /> <input class="span-13"
							type="text" name="qualification" value="${account.qualification}" />
				</fieldset>
			</c:otherwise>
		</c:choose>
		<fieldset>
			<p>
				<label><fmt:message bundle="${msg}" key="common.address" /></label><br />
				<input class="span-13" type="text" name="address"
					value="${account.address}" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.phone" /></label><br />
				<input class="span-13" type="text" name="phone"
					value="${account.phone}" />
			</p>
		</fieldset>
		<c:choose>
			<c:when
				test="${user.role == 'ADMINISTRATOR' and account.role != 'ADMINISTRATOR'}">
				<fieldset>

					<select class="span-6" name="is-active">
						<option value="true" ${account.active ? 'selected' :''}>Active</option>
						<option value="false" ${not account.active ? 'selected' :''}>Not
							Active</option>
					</select> <select class="span-6" name="role">
						<option value="CUSTOMER"
							${account.role == 'CUSTOMER' ? 'selected' : ''}><fmt:message
								bundle="${msg}" key="common.role.customer" /></option>
						<option value="DEVELOPER"
							${account.role == 'DEVELOPER' ? 'selected' : ''}><fmt:message
								bundle="${msg}" key="common.role.developer" /></option>
						<option value="MANAGER"
							${account.role == 'MANAGER' ? 'selected' : ''}><fmt:message
								bundle="${msg}" key="common.role.manager" /></option>
						<option value="ADMINISTRATOR"
							${account.role == 'ADMINISTRATOR' ? 'selected' : ''}><fmt:message
								bundle="${msg}" key="common.role.administrator" /></option>
					</select>
				</fieldset>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="is-active" value="${account.active}" />
				<input type="hidden" name="role" value="${account.role}" />
			</c:otherwise>
		</c:choose>
		<button class="span-14">Save account</button>
		<hr />
	</form>
</div>
