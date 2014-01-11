<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:genericpage sidebar="true" title="Order">
	<jsp:body>
	<h1>New Order</h1>
	<t:status />
	<div class="span-18 last">
		<fieldset>
			<legend>Customer Info</legend>
			First Name: ${user.firstName}<br /> 
			Last Name: ${user.lastName}<br />
			Company: ${user.company}<br /> 
			Position: ${user.position}<br />
			Email: ${user.email}<br /> 
			Phone: ${user.phone}<br /> 
			Address: ${user.address}<br />
		</fieldset>
	</div>
	<div class="span-18">
		<form action="do/create-order" method="post"
				enctype="multipart/form-data">
			<p>
				<label>Subject<br /></label>
				<select class="span-18" name="subject">
					<option value="OTHER" selected>Other</option>
					<option value="APPLICATION_DEVELOPMENT">Application Development</option>
					<option value="APPLICATION_TESTING">Application Testing</option>
					<option value="APPLICATION_SUPPORT">Application Support</option>
				</select>
			</p>
			<p>
				<label>Topic<font color="red">*</font></label>
				<input class="span-18" type="text" name="topic" />
			</p>
			<p>
				<label>Message (up to 1000 symbols)<font color="red">*</font><br /></label>
				<textarea class="span-18" name="message" style="resize: none"
						maxlength="1000"></textarea>
			</p>
			<p>
			<label>Attach file (up to 10MB)<br /></label>
			<input type="file" name="file" />
			</p>
			<button class="span-18" type="submit">Submit</button>
		</form>
	</div>
	</jsp:body>
</t:genericpage>

