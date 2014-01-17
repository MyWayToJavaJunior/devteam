<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericpage sidebar="true" title="Account">
	<jsp:body>
	<div class="span-14 ">
		<form action="do/save-account" method="post">
			<input type="hidden" name="id" value="${account.id}">
			<fieldset>
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.email" />
					</label>
					<input class="span-13" type="text" name="email" disabled="disabled"
						value="${account.email}" />
				</p>
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.firstName" />
					</label>
					<input class="span-13" type="text" name="first-name"
						value="${account.firstName}" title="Enter email" />
				</p>
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.lastName" />
					</label>
					<input class="span-13" type="text" name="last-name"
						value="${account.lastName}" />
				</p>
			</fieldset>
			<c:choose>
				<c:when test="${account.role == 'CUSTOMER'}">
					<fieldset>
						<p>
							<label>
								<fmt:message bundle="${msg}" key="common.company" />
							</label>
							<input class="span-13" type="text" name="company"
								value="${account.company}" />
						</p>
						<p>
							<label>
								<fmt:message bundle="${msg}" key="common.position" />
							</label>
							<input class="span-13" type="text" name="position"
								value="${account.position}" />
						</p>
					</fieldset>
				</c:when>
				<c:otherwise>
					<fieldset>
						<p>
							<label>
								<fmt:message bundle="${msg}" key="common.birthDate" />
							</label><br />
							<t:date />
						</p>
						<p>
							<label><fmt:message bundle="${msg}" key="common.qualification" />
						</label>
						<input class="span-13" type="text" name="qualification"
								value="${account.qualification}" />
					</fieldset>
				</c:otherwise>
			</c:choose>
			<fieldset>
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.address" />
					</label>
					<input class="span-13" type="text" name="address"
						value="${account.address}" />
				</p>
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.phone" />
					</label>
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
						</select> 
						<select class="span-6" name="role">
							<option value="CUSTOMER" ${account.role == 'CUSTOMER' ? 'selected' : ''}>
								<fmt:message bundle="${msg}" key="common.role.customer" />
							</option>
							<option value="DEVELOPER" ${account.role == 'DEVELOPER' ? 'selected' : ''}>
								<fmt:message bundle="${msg}" key="common.role.developer" />
							</option>
							<option value="MANAGER" ${account.role == 'MANAGER' ? 'selected' : ''}>
								<fmt:message bundle="${msg}" key="common.role.manager" />
							</option>
							<option value="ADMINISTRATOR" ${account.role == 'ADMINISTRATOR' ? 'selected' : ''}>
								<fmt:message bundle="${msg}" key="common.role.administrator" />
							</option>
						</select>
					</fieldset>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="is-active" value="${account.active}" />
					<input type="hidden" name="role" value="${account.role}" />
				</c:otherwise>
			</c:choose>
			<button class="span-14">
				<fmt:message bundle="${msg}" key="action.saveAccount" />
			</button>
			<hr />
		</form>
		<form action="do/change-password" method="post">
			<fieldset>
				<input type="hidden" name="id" value="${account.id}">
				<p>
					<label>
						<fmt:message bundle="${msg}" key="common.oldPassword" />
					</label>
					<c:if test="${not empty oldPasswordError}">
						<t:error-info error="${oldPasswordError}" />
					</c:if>
					<input class="span-13" type="password" name="old-password"
						value="${oldPassword}" />
				</p>
				<p>
					<label> 
						<fmt:message bundle="${msg}" key="common.newPassword" />
					</label>
					<c:if test="${not empty passwordError}">
						<t:error-info error="${passwordError}" />
					</c:if>
					<input class="span-13" type="password" name="new-password1"
						value="${newPassword1}" />
				</p>
				<p>
					<label> 
						<fmt:message bundle="${msg}" key="common.confirmPassword" />
					</label> 
					<input class="span-13" type="password" name="new-password2"
						value="${newPassword2}" />
				</p>
				<button class="span-13">
					<fmt:message bundle="${msg}" key="action.changePassword" />
				</button>
			</fieldset>
		</form>
	</div>
	</jsp:body>
</t:genericpage>