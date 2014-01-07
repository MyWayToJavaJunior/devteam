<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div class="span-13 prepend-5 last">
	<c:if test="${not empty accountError}">
		<font color="red"><%=session.getAttribute("accountError")%></font>
	</c:if>
	<c:if test="${not empty message}">
		<font color="blue"><%=session.getAttribute("message")%></font>
	</c:if>
	<c:choose>
		<c:when test="${empty user}">
			<form action="do/create-account" method="post">
				<fieldset>
					<legend>
						User<font color="red">*</font>
					</legend>
					<div class="span-6 prepend-3 last showgrid">
						<p>
							<label>Enter email</label><input type="text" name="email" />
						</p>
						<p>
							<label>Enter password</label><input type="password"
								name="password" />
						</p>
						<p>
							<label>Confirm password</label> <input type="password"
								name="password-confirm" />
						</p>
						<button class="span-6">Create account</button>
					</div>
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<form action="do/modify-account" method="post">
				<fieldset>
					<legend>
						Name<font color="red">*</font>
					</legend>
					<div class="span-12 last">
						<div class="span-6">
							<input type="text" name="first-name" value="${user.firstName}" />
						</div>
						<div class="span-6 last">
							<input type="text" name="last-name" value="${user.lastName}" />
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>
						Company & Position<font color="red">*</font>
					</legend>
					<div class="span-6">
						<input type="text" name="company" value="${user.company}" />
					</div>
					<div class="span-6 last">
						<input type="text" name="position" value="${user.position}" />
					</div>
				</fieldset>
				<fieldset>
					<legend>
						Address & Phone<font color="red">*</font>
					</legend>
					<div class="span-6">
						<input type="text" name="address" value="${user.address}" />
					</div>
					<div class="span-6 last">
						<input type="text" name="phone" value="${user.phone}" />
					</div>
				</fieldset>
				<button class="span-13">Save account</button>
				<hr />
			</form>
		</c:otherwise>
	</c:choose>
</div>
