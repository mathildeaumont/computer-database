<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
<html>
<c:import url="head.jsp" />
<body>
	<mylib:header pageName="error" />
	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				<spring:message code="error.500"/>
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>