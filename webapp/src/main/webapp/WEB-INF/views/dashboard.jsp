<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
    <mylib:header pageName="dashboard" />

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            	${computersNb} <spring:message code="label.found"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="<c:url value="/dashboard" />" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder=<spring:message code="label.search"/> />
                        <input type="submit" id="searchsubmit" value="<spring:message code="label.filter"/>"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                	<sec:authorize access="hasRole('ADMIN')">
	                    <a class="btn btn-success" id="addComputer" href="<c:url value="/add" />"><spring:message code="label.add"/></a> 
	                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="label.edit"/></a>
                    </sec:authorize>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="<c:url value="/delete" />" method="POST">
            <input type="hidden" name="selection" value="">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->
 						<sec:authorize access="hasRole('ADMIN')">
                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        </sec:authorize>
                        <th> 
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.name" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.name"/>
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.name" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.name"/>
									</mylib:link>
								</c:otherwise>
							</c:choose>
			
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.name' && direction == 'asc'}">
										<img src="<c:url value="/resources/img/fleche_bas.png" />" />
									</c:when>
									<c:when test="${order == 'compu.name' && direction == 'desc'}">
										<img src="<c:url value="/resources/img/fleche_haut.png" />" />
									</c:when>
								</c:choose>
							</span>
                        </th>
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.introducedDate" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.introduced"/>
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.introducedDate" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.introduced"/>
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.introducedDate' && direction == 'asc'}">
										<img src="<c:url value="/resources/img/fleche_bas.png" />" />
									</c:when>
									<c:when test="${order == 'compu.introducedDate' && direction == 'desc'}">
										<img src="<c:url value="/resources/img/fleche_haut.png" />" />
									</c:when>
								</c:choose>
							</span>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.discontinuedDate" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.discontinued"/>
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.discontinuedDate" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.discontinued"/>
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.discontinuedDate' && direction == 'asc'}">
										<img src="<c:url value="/resources/img/fleche_bas.png" />" />
									</c:when>
									<c:when test="${order == 'compu.discontinuedDate' && direction == 'desc'}">
										<img src="<c:url value="/resources/img/fleche_haut.png" />" />
									</c:when>
								</c:choose>
							</span>
                        <!-- Table header for Company -->
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.company_name" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.company"/>
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.company_name" search="${search}" nbResults="${page.nbResults}">
										<spring:message code="computer.company"/>
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.company_name' && direction == 'asc'}">
										<img src="<c:url value="/resources/img/fleche_bas.png" />" />
									</c:when>
									<c:when test="${order == 'compu.company_name' && direction == 'desc'}">
										<img src="<c:url value="/resources/img/fleche_haut.png" />" />
									</c:when>
								</c:choose>
							</span>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<sec:authorize access="hasRole('ADMIN')">
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${computer.id}" /></td>
							</sec:authorize>
							<td><a
								href="<c:url value="/edit?id=${computer.id}" />">${computer.name}</a>
							</td>
							<td>${computer.introducedDate}</td>
							<td>${computer.discontinuedDate}</td>
							<td><c:choose>
									<c:when test="${empty computer.company}">
									</c:when>
									<c:otherwise>
                            			${computer.company.name}
                        			</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
		<mylib:pagination current="${page.currentPage}" nbResults="${page.nbResults}" />
    </footer>
	
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>
	
</body>
</html>