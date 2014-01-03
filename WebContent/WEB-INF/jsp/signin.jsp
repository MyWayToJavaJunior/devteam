<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign in</title>
<%@include file="cssimport.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container_16" id="page_body">
		<div class="grid_4" style="height: 1px"></div>
		<div class="grid_8">
			<div class="box" align="center">
				<form action="POST/signin">
					<fieldset>
						<legend>Sign in</legend>
						<label>Email</label><br>
						<input type="text" name="username" /><br>
						<label>Password</label><br>
						<input type="password" name="password" /><br>
						<input type="submit" value="Sign in"/>
					</fieldset>
				</form>
				<form action="POST/signup">
					<fieldset>
						<legend>Sign up</legend>
						<label>Email:</label><br>
						<input type="text" name="email"/><br>
						<label>Create password:</label><br>
						<input type="password" name="password"/><br>
						<label>Confirm password: </label><br>
						<input type="password" name="password2"/><br>
						<label>First Name: </label><br> 
						<input type="text" name="first_name"/><br>
						<label>Last Name:</label><br>
						<input type="text" name="last_name"/><br>
						<label>Address:</label><br>
						<input type="text" name="address"/><br>
						<label>Phone number:</label><br>
						<input type="text" name="phone"/><br>
						<label>Country: </label><br>
						<select name="Country">
							<option value="">Select Country...</option>
							<option value="Canada">Canada</option>
							<option value="United States">United States</option>
						</select><br>
						<input type="submit" value="Sign up"/>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="grid_4" style="height: 1px"></div>
	</div>
	<div class="clear"></div>
	<%@include file="footer.jsp"%>
</body>
</html>