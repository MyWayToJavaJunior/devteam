<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Request</title>
<link rel="stylesheet" type="text/css" href="/devteam/css/reset.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="/devteam/css/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="/devteam/css/960.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="/devteam/css/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="/devteam/css/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="/devteam/css/nav.css"
	media="screen" />
</head>
<body>
	<div class="container_16">
		<!--Header-->
		<div class="grid_16">
			<h1 id="branding">
				<a href="main">Development team</a>
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
				<li><a href="request">Online request</a></li>
				<li class="secondary"><a href="login" title="Sign in">Sign
						in</a></li>
			</ul>
		</div>
		<div class="clear"></div>
		<!--Page title-->
		<div class="grid_16">
			<h3 id="page_title" align="center">Leave your request.</h3>
		</div>
		<div class="clear"></div>
		<!--Site body-->
		<div class="grid_4" style="height: 1px"></div>
		<div class="grid_8">
			<form>
				<fieldset>
					<legend>Request</legend>
					<p>
						<label>First name</label> <input type="text" name="first_name"
							value="" />
					</p>
					<p>
						<label>Last name</label> <input type="text" name="last_name"
							value="" />
					</p>
					<p>
						<label>Company</label> <input type="text" name="company" value="" />
					</p>
					<p>
						<label>Position</label> <input type="text" name="position"
							value="" />
					</p>
					<p>
						<label>Contact phone</label> <input type="text" name="phone"
							value="" />
					</p>
					<p>
						<label>Contact email</label> <input type="text" name="email"
							value="" />
					</p>
					<p>
						<label>Country</label> <input type="text" name="country" value="" />
					</p>
					<p>
						<label>Address</label> <input type="text" name="address" value="" />
					</p>
					<p>
						<label>Request message</label>
						<textarea cols="107" rows="6" name="message"></textarea>
					</p>
					<p>
						<label>Attach file</label> <input type="file" size="40">
					</p>

					<input type="submit" value="Submit">
				</fieldset>
			</form>
		</div>
		<div class="grid_4" style="height: 1px"></div>
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