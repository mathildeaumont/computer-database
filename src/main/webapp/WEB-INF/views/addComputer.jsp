<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
	<c:import url="header.jsp" />
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="<c:url value="/add" />" method="POST" id="addComputerForm">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="name" value="${name}" placeholder="Computer name">
                                <div id="errorName">${errorName}</div>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="datetime" class="form-control" id="introduced" name="introduced" value="${introduced}" placeholder="Introduced date">
                                <div id="errorIntroduced">${errorIntroduced}</div>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="datetime" class="form-control" id="discontinued" name="discontinued" value="${discontinued}" placeholder="Discontinued date">
                                <div id="errorDiscontinued">${errorDiscontinued}</div>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                	<c:forEach items="${companies}" var="company">
                                		<option value="${company.id}" <c:if test="${company.id == companyId }">selected</c:if>>${company.name}</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/validate.js"></script>
    
</body>
</html>