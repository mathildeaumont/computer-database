<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
	<mylib:header pageName="add" />
	
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="label.add"/></h1>
                    <form:form action="add" method="POST" id="addComputerForm" commandName="addComputerForm">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name"/></label>
                                <form:input type="text" class="form-control" id="computerName" name="name" value="${name}" placeholder="Computer name" path="name" />
                                <div id="errorName">${errorName}</div>
                                <form:errors path="name" />
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="computer.introduced"/></label>
                                <form:input type="datetime" class="form-control" id="introduced" name="introduced" value="${introduced}" placeholder="Introduced date" path="introducedDate" />
                                <div id="errorIntroduced">${errorIntroduced}</div>
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="computer.discontinued"/>e</label>
                                <form:input type="datetime" class="form-control" id="discontinued" name="discontinued" value="${discontinued}" placeholder="Discontinued date" path="discontinuedDate"/>
                                <div id="errorDiscontinued">${errorDiscontinued}</div>
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="computer.company"/></label>
                                <form:select class="form-control" id="companyId" name="companyId" path="company.id">
                                	<c:forEach items="${companies}" var="company">
                                		<option value="${company.id}" <c:if test="${company.id == companyId }">selected</c:if>>${company.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.add"/>" class="btn btn-primary">
                            <spring:message code="label.or"/>
                            <a href="dashboard" class="btn btn-default"><spring:message code="label.cancel"/></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
    
    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
	<script src="<c:url value="/resources/js/validate.js" />"></script>
</body>
</html>