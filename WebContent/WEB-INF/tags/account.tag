<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<div id="account" class="span-4">
	<c:choose>
		<c:when test="${not empty user}">
			<fieldset>
				<legend>${user.role}</legend>
				<p>
					<label>Name: </label>${user.firstName} ${user.lastName}
				</p>
				<c:if test="${user.role == 'CUSTOMER'}">
					<p>
						<label>Company: </label>${user.company}
					</p>
					<p>
						<label>Position: </label>${user.position}
					</p>
				</c:if>
				<form action="do/signout" method="get">
					<button class="span-4">Sign out</button>
				</form>
				<form action="do/user-account" method="get">
					<button class="span-4" type="submit">Edit account</button>
				</form>
			</fieldset>

		</c:when>
		<c:otherwise>
			<form action="do/signin" method="post">
				<fieldset>
					<legend>Sign in</legend>
					<t:status />
					<p>
						<label for="email">Email</label> <input id="email" class="span-4"
							type="text" name="email" />
					</p>
					<p>
						<label for="email">Password</label> <input class="span-4"
							type="password" name="password" />
					</p>
					<button class="span-4">Sign in</button>
					<a href="do/user-account">Create account</a>
				</fieldset>
			</form>
		</c:otherwise>
	</c:choose>
</div>

