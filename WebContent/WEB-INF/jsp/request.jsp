<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Request</title>
<%@include file="cssimport.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container_16" id="page_body">
		<div class="clear"></div>
		<!--Site body-->
		<div class="grid_4" style="height: 1px"></div>
		<div class="grid_8">
		<div class="box" align="center">
			<form action="request_create" method="post">
				<fieldset>
					<legend>Request</legend>
					<input type="hidden" name="command" value="request_create">
					<label>First name</label><br>
					<input type="text" name="first_name"/><br>
					<label>Last name</label><br>
					<input type="text" name="last_name"/><br>
					<label>Company</label><br>
					<input type="text" name="company"/><br>
					<label>Position</label><br>
					<input type="text" name="position"/><br>
					<label>Contact phone</label><br>
					<input type="text" name="phone"/><br>
					<label>Contact email</label><br>
					<input type="text" name="email"/><br>
					<label>Country</label><br>
					<input type="text" name="country"/><br>
					<label>Address</label><br>
					<input type="text" name="address"/><br>
					<label>Request message</label><br>
					<textarea cols="50" rows="6" name="message"></textarea><br>
					<label>Attach file</label> <input type="file" size="40"><br>
					<input type="submit" value="Submit">
				</fieldset>
			</form>
			</div>
		</div>
		<div class="grid_4" style="height: 1px"></div>
		<div class="clear"></div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>