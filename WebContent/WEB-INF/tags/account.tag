<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div id="account" class="span-4">
	<c:choose>
		<c:when test="${not empty user}">
			<fieldset>
				<legend>
					<t:role-info role="${user.role}"></t:role-info>
				</legend>
				<p>
					<label> <fmt:message bundle="${msg}" key="common.user" />:
					</label> ${user.firstName} ${user.lastName}
				</p>
				<c:if test="${user.role == 'CUSTOMER'}">
					<p>
						<label> <fmt:message bundle="${msg}" key="common.company" />:
						</label> ${user.company}
					</p>
					<p>
						<label> <fmt:message bundle="${msg}" key="common.position" />:
						</label> ${user.position}
					</p>
				</c:if>
				<form action="do/signout" method="get">
					<button class="span-4">
						<fmt:message bundle="${msg}" key="action.signOut" />
					</button>
				</form>
				<form action="do/edit-account" method="get">
					<button class="span-4" type="submit">
						<fmt:message bundle="${msg}" key="action.editAccount" />
					</button>
				</form>
			</fieldset>

		</c:when>
		<c:otherwise>
			<form action="do/signin" method="post">
				<fieldset>
					<legend>
						<fmt:message bundle="${msg}" key="action.signIn" />
					</legend>
					<p>
						<label><fmt:message bundle="${msg}" key="common.email" /></label>
						<input class="span-4" type="text" name="email" />
					</p>
					<p>
						<label> <fmt:message bundle="${msg}" key="common.password" />
						</label> <input class="span-4" type="password" name="password" />
					</p>
					<button class="span-4">
						<fmt:message bundle="${msg}" key="action.signIn" />
					</button>
					<a href="do/create-account"> <fmt:message bundle="${msg}"
							key="action.createAccount" />
					</a>
				</fieldset>
			</form>
		</c:otherwise>
	</c:choose>
</div>

