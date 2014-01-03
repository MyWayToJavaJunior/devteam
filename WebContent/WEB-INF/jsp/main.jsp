<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/960.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
</head>
<body>
	<div class="container_16">
		<!--Header-->
		<div class="grid_16">
			<h1 id="branding">
				<a href="">Development team</a>
			</h1>
		</div>
		<div class="clear"></div>
		<div class="grid_16">
			<ul class="nav main">
				<li><a href="">Company</a>
					<ul>
						<li><a href="">Services</a></li>
						<li><a href="">About us</a></li>
						<li><a href="">Contact us</a></li>
					</ul></li>
				<li><a href="">Technical support</a>
					<ul>
						<li><a href="">Downloads</a></li>
						<li><a href="">Documentation</a></li>
					</ul></li>
				<li><a href="GET/request">Online request</a></li>
				<li class="secondary"><a href="GET/login" title="Sign in">Sign
						in</a></li>
			</ul>
		</div>
		<div class="clear"></div>
		<!--Page title-->
		<div class="grid_16">
			<h3 id="page_title" align="center">Welcome to the development
				team site!</h3>
		</div>
		<div class="clear"></div>
		<!--Site body-->
		<div class="grid_16">
			<h1 align="center">Page body</h1>
		</div>
		<div class="clear"></div>
		<!--Footer-->
		<div class="grid_16" id="site_info">
			<div class="box">
				This site was developed by <a href="">Andrey Kovalskiy</a> for <b>JavaLab</b>
				course.
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>