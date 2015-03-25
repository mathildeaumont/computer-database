<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="current" required="true" type="java.lang.Integer"
	description="currentPage"%>
<%@ attribute name="nbResults" required="true" type="java.lang.Integer"
	description="nbResults"%>
<div class="container text-center">
	<ul class="pagination">
		<c:if test="${current > 2}">
			<li>
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="1" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
				aria-label="Previous"> <span aria-hidden=true>&laquo;</span>
				</a>
			</li>
		</c:if>
		<c:if test="${current != 1}">
			<li>
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="${current-1}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
				aria-label="Previous"> <span aria-hidden=true>&lt;</span>
				</a>
			</li>
		</c:if>
		<c:if test="${current-2 > 0}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${current-2}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
				>${current-2}
				</a>
			</li>
		</c:if>
		<c:if test="${current-1 > 0}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${current-1}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
					>${current-1}
				</a>
			</li>
		</c:if>
		<li class="active"><a href="#">${current}</a></li>
		<c:if test="${current+1 <= page.totalPages}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${current+1}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>">${current+1}
				</a>
			</li>
		</c:if>
		<c:if test="${current+2 <= page.totalPages}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${current+2}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
					>${current+2}
				</a>
			</li>
		</c:if>
		<c:if test="${current != page.totalPages}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${current+1}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
					aria-label="Next"> 
					<span aria-hidden="true">&gt;</span>
				</a>
			</li>
		</c:if>
		<c:if test="${current < page.totalPages - 1}">
			<li><a href="<c:url value="dashboard">
							<c:param name="offset" value="${page.totalPages}" />
							<c:param name="nbResults" value="${nbResults}" />
						</c:url>"
					aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?nbResults=10'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?nbResults=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?nbResults=100'">100</button>
	</div>
</div>