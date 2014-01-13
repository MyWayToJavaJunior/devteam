<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="localAccount" required="true"
	type="com.epam.devteam.entity.user.User"%>
<div class="span-14">
	<div>
		<t:status />
	</div>
	<form action="do/save-account" method="post">
		<input type="hidden" name="id" value="${localAccount.id}">
		<fieldset>
			<legend>
				Name<font color="red">*</font>
			</legend>
			<div class="span-6 colborder">
				<input type="text" name="first-name"
					value="${localAccount.firstName}" />
			</div>
			<div class="span-6 last">
				<input type="text" name="last-name" value="${localAccount.lastName}" />
			</div>
		</fieldset>
		<c:choose>
			<c:when test="${localAccount.role == 'CUSTOMER'}">
				<fieldset>
					<legend>
						Company & Position<font color="red">*</font>
					</legend>
					<div class="span-6 colborder">
						<input type="text" name="company" value="${localAccount.company}" />
					</div>
					<div class="span-6 last">
						<input type="text" name="position"
							value="${localAccount.position}" />
					</div>
				</fieldset>
			</c:when>
			<c:otherwise>
				<fieldset>
					<legend>
						Birth Date & Qualification<font color="red">*</font>
					</legend>
					<div class="span-6 colborder">
						<t:date />
					</div>
					<div class="span-6 last">
						<input type="text" name="qualification"
							value="${localAccount.qualification}" />
					</div>
				</fieldset>
			</c:otherwise>
		</c:choose>
		<fieldset>
			<legend>
				Address & Phone<font color="red">*</font>
			</legend>
			<div class="span-6 colborder">
				<input type="text" name="address" value="${localAccount.address}" />
			</div>
			<div class="span-6 last">
				<input type="text" name="phone" value="${localAccount.phone}" />
			</div>
		</fieldset>
		<fieldset>
			<legend>
				Status & Role<font color="red">*</font>
			</legend>
			<c:choose>
				<c:when
					test="${user.role == 'ADMINISTRATOR' and localAccount.role != 'ADMINISTRATOR'}">
					<div class="span-6 colborder">
						<select class="span-6" name="is-active">
							<option value="true" ${localAccount.active ? 'selected' :''}>Active</option>
							<option value="false" ${not localAccount.active ? 'selected' :''}>Not
								Active</option>
						</select>
					</div>
					<div class="span-6 last">
						<select class="span-6" name="role">
							<option value="CUSTOMER"
								${localAccount.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
							<option value="DEVELOPER"
								${localAccount.role == 'DEVELOPER' ? 'selected' : ''}>Developer</option>
							<option value="MANAGER"
								${localAccount.role == 'MANAGER' ? 'selected' : ''}>Manager</option>
							<option value="ADMINISTRATOR"
								${localAccount.role == 'ADMINISTRATOR' ? 'selected' : ''}>Administrator</option>
						</select>
					</div>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="is-active"
						value="${localAccount.active}" />
					<input type="hidden" name="role" value="${localAccount.role}" />
					<div class="span-6 colborder">${localAccount.active ? 'Active' : 'Not Active'}
					</div>
					<div class="span-6 last">${localAccount.role}</div>
				</c:otherwise>
			</c:choose>
		</fieldset>
		<button class="span-14">Save account</button>
		<hr />
	</form>
</div>
