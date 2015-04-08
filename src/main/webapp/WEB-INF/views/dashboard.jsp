<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
    <c:import url="header.jsp" />

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            	${computersNb} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="<c:url value="/dashboard" />" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="<c:url value="/add" />">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="<c:url value="/delete" />" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th> 
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.name" search="${search}" nbResults="${page.nbResults}">
										Computer name
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.name" search="${search}" nbResults="${page.nbResults}">
										Computer name
									</mylib:link>
								</c:otherwise>
							</c:choose>
			
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.name' && direction == 'asc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_bas.png" />
									</c:when>
									<c:when test="${order == 'compu.name' && direction == 'desc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_haut.png" />
									</c:when>
								</c:choose>
							</span>
                        </th>
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.introduced" search="${search}" nbResults="${page.nbResults}">
										Introduced date
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.introduced" search="${search}" nbResults="${page.nbResults}">
										Introduced date
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.introduced' && direction == 'asc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_bas.png" />
									</c:when>
									<c:when test="${order == 'compu.introduced' && direction == 'desc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_haut.png" />
									</c:when>
								</c:choose>
							</span>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="compu.discontinued" search="${search}" nbResults="${page.nbResults}">
										Discontinued date
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="compu.discontinued" search="${search}" nbResults="${page.nbResults}">
										Discontinued date
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compu.discontinued' && direction == 'asc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_bas.png" />
									</c:when>
									<c:when test="${order == 'compu.discontinued' && direction == 'desc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_haut.png" />
									</c:when>
								</c:choose>
							</span>
                        <!-- Table header for Company -->
                        <th>
							<c:choose>
								<c:when test="${direction == 'asc'}">
									<mylib:link url="dashboard" direction="desc" offset="1" order="company.name" search="${search}" nbResults="${page.nbResults}">
										Company
									</mylib:link>
								</c:when>
								<c:otherwise>
									<mylib:link url="dashboard" direction="asc" offset="1" order="company.name" search="${search}" nbResults="${page.nbResults}">
										Company
									</mylib:link>
								</c:otherwise>
							</c:choose>
							
							<span class="right">
								<c:choose>
									<c:when test="${order == 'compa.name' && direction == 'asc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_bas.png" />
									</c:when>
									<c:when test="${order == 'compa.name' && direction == 'desc'}">
										<img src="${pageContext.request.contextPath}/img/fleche_haut.png" />
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
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}" /></td>
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
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>