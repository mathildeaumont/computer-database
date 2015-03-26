<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> Application - Computer Database </a>
        </div>
    </header>

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

        <form id="deleteForm" action="#" method="POST">
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
	                        <a href="<c:url value="dashboard">
										<c:param name="offset" value="1" />
										<c:param name="nbResults" value="${page.nbResults}" />
										<c:param name="order" value="compu.name" />
										<c:choose>
											<c:when test="${direction == 'asc'}">
												<c:param name="direction" value="desc" />
											</c:when>
											<c:otherwise>
												<c:param name="direction" value="asc" />
											</c:otherwise>
										</c:choose>
									</c:url>">
									Computer name
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
							</a>
                        </th>
                        <th>
                            <a href="<c:url value="dashboard">
										<c:param name="offset" value="1" />
										<c:param name="nbResults" value="${page.nbResults}" />
										<c:param name="order" value="compu.introduced" />
										<c:choose>
											<c:when test="${direction == 'asc'}">
												<c:param name="direction" value="desc" />
											</c:when>
											<c:otherwise>
												<c:param name="direction" value="asc" />
											</c:otherwise>
										</c:choose>
									</c:url>">
									Introduced date
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
							</a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <a href="<c:url value="dashboard">
										<c:param name="offset" value="1" />
										<c:param name="nbResults" value="${page.nbResults}" />
										<c:param name="order" value="compu.discontinued" />
										<c:choose>
											<c:when test="${direction == 'asc'}">
												<c:param name="direction" value="desc" />
											</c:when>
											<c:otherwise>
												<c:param name="direction" value="asc" />
											</c:otherwise>
										</c:choose>
									</c:url>">
									Discontinued date
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
							</a>
                        <!-- Table header for Company -->
                        <th>
                            <a href="<c:url value="dashboard">
										<c:param name="offset" value="1" />
										<c:param name="nbResults" value="${page.nbResults}" />
										<c:param name="order" value="compa.name" />
										<c:choose>
											<c:when test="${direction == 'asc'}">
												<c:param name="direction" value="desc" />
											</c:when>
											<c:otherwise>
												<c:param name="direction" value="asc" />
											</c:otherwise>
										</c:choose>
									</c:url>">
									Company
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
							</a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0" /></td>
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