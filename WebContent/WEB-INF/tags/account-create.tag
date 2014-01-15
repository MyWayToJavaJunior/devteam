<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="span-6">
	<form action="do/create-account" method="post">
		<fieldset>
			<legend>
				<fmt:message bundle="${msg}" key="common.user" />
			</legend>
			<p>
				<label> <fmt:message bundle="${msg}" key="common.email" />
				</label>
				<c:if test="${not empty emailError}">
					<t:error-info error="${emailError}" />
				</c:if>
				<input type="text" name="email" value="${email}" />
			</p>
			<p>
				<label> <fmt:message bundle="${msg}"
						key="common.createPassword" />
				</label>
				<c:if test="${not empty passwordError}">
					<t:error-info error="${passwordError}" />
				</c:if>
				<input type="password" name="password1" value="${password1}" />
			</p>
			<p>
				<label> <fmt:message bundle="${msg}"
						key="common.confirmPassword" />
				</label>
				<c:if test="${not empty passwordConfirmError}">
					<t:error-info error="${passwordConfirmError}" />
				</c:if>
				<input type="password" name="password2" value="${password2}" />
			</p>
			<c:choose>
				<c:when test="${not empty user and user.role == 'ADMINISTRATOR'}">
					<p>
						<label>Role</label> <select class="span-6" name="role">
							<option value="CUSTOMER" selected><fmt:message
									bundle="${msg}" key="common.role.customer" /></option>
							<option value="DEVELOPER"><fmt:message bundle="${msg}"
									key="common.role.developer" /></option>
							<option value="MANAGER"><fmt:message bundle="${msg}"
									key="common.role.manager" /></option>
							<option value="ADMINISTRATOR"><fmt:message
									bundle="${msg}" key="common.role.administrator" /></option>
						</select>
					</p>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="role" value="CUSTOMER">
				</c:otherwise>
			</c:choose>
			<button class="span-6">
				<fmt:message bundle="${msg}" key="action.createAccount" />
			</button>
		</fieldset>
	</form>
</div>