<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="span-6">
	<div>
		<t:status />
	</div>
	<form action="do/create-account" method="post">
		<fieldset>
			<legend>
				User<font color="red">*</font>
			</legend>
			<p>
				<label>Enter email</label> <input type="text" name="email" />
			</p>
			<p>
				<label>Enter password</label> <input type="password"
					name="password1" />
			</p>
			<p>
				<label>Confirm password</label> <input type="password"
					name="password2" />
			</p>
			<c:choose>
				<c:when test="${not empty user and user.role == 'ADMINISTRATOR'}">
					<p>
						<label>Role</label> <select class="span-6" name="role">
							<option value="CUSTOMER" selected>Customer</option>
							<option value="DEVELOPER">Developer</option>
							<option value="MANAGER">Manager</option>
							<option value="ADMINISTRATOR">Administrator</option>
						</select>
					</p>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="role" value="CUSTOMER">
				</c:otherwise>
			</c:choose>
			<button class="span-6">Create account</button>
		</fieldset>
	</form>
</div>