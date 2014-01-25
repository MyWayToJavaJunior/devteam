<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:genericpage sidebar="true" title="Main">
	<jsp:body>
	<h1>
			<fmt:message bundle="${msg}" key="common.applicationDevelopment" />
		</h1>
	<div class="span-14">
			<img src="static/development.jpg">
		</div>
	<div class="span-4 last">
	 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras molestie ultrices diam, vel adipiscing est auctor eu. Nulla egestas ultrices lectus, ut cursus risus semper at. Aliquam vulputate lectus id ligula dapibus porttitor. Aliquam scelerisque elit nec lacus bibendum fermentum. Integer placerat est tortor. Pellentesque cursus eu orci non suscipit.
	</div>
	<h1>
		<fmt:message bundle="${msg}" key="common.applicationTesting" />
	</h1>
	<div class="span-11">Sed fermentum mauris porta lacinia rhoncus. Duis sit amet odio id leo ullamcorper cursus vel at nunc. In id libero dignissim leo elementum placerat in at ante. Donec lacinia, ligula eu venenatis elementum, arcu mauris feugiat tellus, sit amet lacinia orci felis sed elit. Cras at purus a ligula dictum fermentum. Proin condimentum aliquet sodales. Ut felis nisi, porttitor in tellus sed, bibendum pellentesque neque. Ut egestas mi vel enim consequat, vitae rhoncus lacus pulvinar. Vestibulum tempus dolor ligula. In vitae odio lectus. Donec consequat libero eget ipsum tincidunt lobortis. Nulla tincidunt velit sed nisi lacinia ornare. Sed vel mollis quam. Duis pretium quam mauris, eu sagittis mi eleifend at. </div>
	<div class="span-7 last">
		<img src="static/testing.jpeg">
	</div>
	<h1>
		<fmt:message bundle="${msg}" key="common.applicationSupport" />
	</h1>
	<div class="span-7">
			<img src="static/support.jpg">
		</div>
	<div class="span-11 last"> 
		Pellentesque cursus sapien fermentum, consectetur nisi faucibus, porttitor nibh. Duis tempor, mauris ut dignissim convallis, enim mauris pellentesque risus, vel consectetur purus elit ut diam. In luctus purus nec mauris congue volutpat. Suspendisse feugiat consectetur imperdiet. Aliquam bibendum, quam sed fringilla commodo, nisl tortor adipiscing felis, in rutrum tellus velit quis nisl. Donec non purus adipiscing, consectetur felis ut, euismod lorem. Integer viverra sit amet felis et aliquet. Integer id ipsum bibendum, aliquet mauris in, rutrum velit. Sed eu sem felis. Vestibulum tortor ipsum, accumsan sit amet risus vitae, tempor euismod nulla. Ut sagittis imperdiet metus, nec blandit felis consequat quis. Nunc sit amet odio id neque fringilla eleifend. 
	</div>	
	</jsp:body>
</t:genericpage>