<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="span-20">
	This site was developed by <b>Andrey Kovalskiy</b> for <a>JavaLab</a>
	courses.
</div>
<div class="span-4 last">
	<c:set var="currentLocale"
		value="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}" />
	<form action="do/set-language" method="post">
		<select class="span-4" name="locale" onchange="submit()">
			<option value="en" ${currentLocale == 'en' ? 'selected' : ''}>English</option>
			<option value="ru" ${currentLocale == 'ru' ? 'selected' : ''}>Русский</option>
		</select>
	</form>
</div>
