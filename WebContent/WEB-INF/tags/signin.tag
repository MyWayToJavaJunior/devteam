<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="account" class="span-4">
	<c:choose>
		<c:when test="${not empty user}">
			<form action="do/signout" method="get">
				<fieldset>
					<legend>${user.role}</legend>
					<p>
						<label>Name: </label>${user.firstName} ${user.lastName}
					</p>
					<p>
						<label>Company: </label>${user.company}
					</p>
					<p>
						<label>Position: </label>${user.position}
					</p>
					<a href="do/account">Manage account</a>
					<button class="span-4">Sign out</button>
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<form action="do/signin" method="post">
				<fieldset>
					<legend>Sign in</legend>
					<c:if test="${not empty signinError}">
						<font color="red"><%=session.getAttribute("signinError")%></font>
					</c:if>
					<p>
						<label for="email">Email</label> <input id="email" class="span-4"
							type="text" name="email" />
					</p>
					<p>
						<label for="email">Password</label> <input class="span-4"
							type="password" name="password" />
					</p>
					<button class="span-4">Sign in</button>
					<a href="do/main">Forgot password</a><br /> <a href="do/account">Create
						account</a>
				</fieldset>
			</form>
		</c:otherwise>
	</c:choose>
</div>

