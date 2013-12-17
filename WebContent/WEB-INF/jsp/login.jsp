<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	Hello from login page!
	<form action="/devteam/do/login" method="post">
		<input type="hidden" name="command" value="login">
		Input your username and password: <br/>
		<input type="text" name="username" value="username" /> <br/>
		<input type="password" name="password" value="" /> <br/>
		<input type="submit" value="enter" />	
	</form>
</body>
</html>