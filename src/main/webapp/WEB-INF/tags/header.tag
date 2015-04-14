<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pageName" required="true" type="java.lang.String"
	description="pageName"%>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="dashboard"> Application
			- Computer Database </a> <span style="float: right"> 
			
		<c:if test="${pageName == 'dashboard'}">
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="nbResults" value="${page.nbResults}" />
							<c:param name="order" value="${order}" />
							<c:param name="direction" value="${direction}" />
							<c:param name="search" value="${search}" />
							<c:param name="lang" value="en" />
						</c:url>">
				en
				</a>
				|
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="nbResults" value="${page.nbResults}" />
							<c:param name="order" value="${order}" />
							<c:param name="direction" value="${direction}" />
							<c:param name="search" value="${search}" />
							<c:param name="lang" value="fr" />
						</c:url>">
				fr
				</a>
		</c:if>
		
		<c:if test="${pageName == 'add'}">
				<a href="<c:url value="add">
							<c:param name="lang" value="en" />
						</c:url>">
				en
				</a>
				|
				<a href="<c:url value="add">
							<c:param name="lang" value="fr" />
						</c:url>">
				fr
				</a>
		</c:if>
		
		<c:if test="${pageName == 'edit'}">
				<a href="<c:url value="edit">
							<c:param name="id" value="${computer.id}" />
							<c:param name="lang" value="en" />
						</c:url>">
				en
				</a>
				|
				<a href="<c:url value="edit">
							<c:param name="id" value="${computer.id}" />
							<c:param name="lang" value="fr" />
						</c:url>">
				fr
				</a>
		</c:if>
		</span>
	</div>
</header>