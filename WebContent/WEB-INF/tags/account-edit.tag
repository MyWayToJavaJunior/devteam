<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="localAccount" required="true"
	type="com.epam.devteam.entity.user.User"%>
<div class="span-14 ">
	<form action="do/save-account" method="post">
		<input type="hidden" name="id" value="${localAccount.id}">
		<fieldset>
			<p>
				<label><fmt:message bundle="${msg}" key="common.email" /></label><br />
				<input class="span-13" type="text" name="email" disabled="disabled"
					value="${localAccount.email}" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.firstName" /></label><br />
				<input class="span-13" type="text" name="first-name"
					value="${localAccount.firstName}" title="Enter email" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.lastName" /></label><br />
				<input class="span-13" type="text" name="last-name"
					value="${localAccount.lastName}" />
			</p>
		</fieldset>
		<c:choose>
			<c:when test="${localAccount.role == 'CUSTOMER'}">
				<fieldset>
					<p>
						<label><fmt:message bundle="${msg}" key="common.company" /></label><br />
						<input class="span-13" type="text" name="company"
							value="${localAccount.company}" />
					</p>
					<p>
						<label><fmt:message bundle="${msg}" key="common.position" /></label><br />
						<input class="span-13" type="text" name="position"
							value="${localAccount.position}" />
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
							type="text" name="qualification"
							value="${localAccount.qualification}" />
				</fieldset>
			</c:otherwise>
		</c:choose>
		<fieldset>
			<p>
				<label><fmt:message bundle="${msg}" key="common.address" /></label><br />
				<input class="span-13" type="text" name="address"
					value="${localAccount.address}" />
			</p>
			<p>
				<label><fmt:message bundle="${msg}" key="common.phone" /></label><br />
				<input class="span-13" type="text" name="phone"
					value="${localAccount.phone}" />
			</p>
		</fieldset>
		<c:choose>
			<c:when
				test="${user.role == 'ADMINISTRATOR' and localAccount.role != 'ADMINISTRATOR'}">
				<fieldset>

					<select class="span-6" name="is-active">
						<option value="true" ${localAccount.active ? 'selected' :''}>Active</option>
						<option value="false" ${not localAccount.active ? 'selected' :''}>Not
							Active</option>
					</select> <select class="span-6" name="role">
						<option value="CUSTOMER"
							${localAccount.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
						<option value="DEVELOPER"
							${localAccount.role == 'DEVELOPER' ? 'selected' : ''}>Developer</option>
						<option value="MANAGER"
							${localAccount.role == 'MANAGER' ? 'selected' : ''}>Manager</option>
						<option value="ADMINISTRATOR"
							${localAccount.role == 'ADMINISTRATOR' ? 'selected' : ''}>Administrator</option>
					</select>
				</fieldset>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="is-active" value="${localAccount.active}" />
				<input type="hidden" name="role" value="${localAccount.role}" />
			</c:otherwise>
		</c:choose>
		<button class="span-14">Save account</button>
		<hr />
	</form>
</div>
