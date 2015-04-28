<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
	<mylib:header pageName="login" />
	
	<section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
					<c:if test="${not empty error}">
						<div class="alert alert-danger"><spring:message code="login.error"/></div>
					</c:if>
					<c:if test="${not empty success}">
						<div class="alert alert-success"><spring:message code="login.success"/></div>
					</c:if>
				
					<form name='loginForm' action="<c:url value='/perform_login' />" method='POST'>
						<fieldset>
						 	<div class="form-group">
                                <label for="computerName"><spring:message code="login.username"/></label>
                                <input type="text" class="form-control" id="username" name="username" value=""  />
                                <div id="errorName">${errorName}</div>
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="login.password"/></label>
                                <input type="password" class="form-control" id="password" name="password" />
                            </div>
				
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</fieldset>
						<div class="actions pull-right">
							<button id="loginForm" type="submit" class="btn btn-success">
								<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>   
								<spring:message code="login.connect"/>
							</button>
                        </div>
					</form>
   				</div>
            </div>
        </div>
    </section>
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>