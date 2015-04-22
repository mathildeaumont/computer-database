<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="pageName" required="true" type="java.lang.String"
	description="pageName"%>

<script type="text/javascript">
	var strings = new Array();
 	strings['alert'] = "<spring:message code='delete.alert' javaScriptEscape='true' />"
 	strings['view'] = "<spring:message code='edit.view' javaScriptEscape='true' />"
	strings['edit'] = "<spring:message code='edit.edit' javaScriptEscape='true' />"
	strings['lang'] = "<spring:message code='lang' javaScriptEscape='true' />"
</script>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		
		<a class="navbar-brand"
				href="dashboard"> <spring:message code="header.title"/> </a> 
		<c:if test="${pageName != 'login'}">
				<div id="logout"> 
					<c:url var="logoutUrl" value="/logout"/>
					<form action="${logoutUrl}" method="post">
						<button type="submit" class="btn btn-danger">
						  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> <spring:message code="login.logout"/>
						</button>
				  		
				  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
		</c:if>			
			
		<span class="flags"> 
		
		<c:if test="${pageName == 'dashboard'}">
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="nbResults" value="${page.nbResults}" />
							<c:param name="order" value="${order}" />
							<c:param name="direction" value="${direction}" />
							<c:param name="search" value="${search}" />
							<c:param name="lang" value="en" />
						</c:url>">
				<img id="en" class="flagEn" src="<c:url value="/resources/img/en.png" />" width=40 height=20 />
				</a>
				
				<a href="<c:url value="dashboard">
							<c:param name="offset" value="${page.currentPage}" />
							<c:param name="nbResults" value="${page.nbResults}" />
							<c:param name="order" value="${order}" />
							<c:param name="direction" value="${direction}" />
							<c:param name="search" value="${search}" />
							<c:param name="lang" value="fr" />
						</c:url>">
				<img id="fr" class="flagFr" src="<c:url value="/resources/img/fr.png" />" width=40 height=20 />
				</a>
		</c:if>
		
		<c:if test="${pageName == 'add'}">
				<a href="<c:url value="add">
							<c:param name="lang" value="en" />
						</c:url>">
				<img id="en" src="<c:url value="/resources/img/en.png" />" width=40 height=20 />
				</a>
				
				<a href="<c:url value="add">
							<c:param name="lang" value="fr" />
						</c:url>">
				<img id="fr" src="<c:url value="/resources/img/fr.png" />" width=40 height=20 />
				</a>
		</c:if>
		
		<c:if test="${pageName == 'edit'}">
				<a href="<c:url value="edit">
							<c:param name="id" value="${computer.id}" />
							<c:param name="lang" value="en" />
						</c:url>">
				<img id="en" src="<c:url value="/resources/img/en.png" />" width=40 height=20 />
				</a>
				
				<a href="<c:url value="edit">
							<c:param name="id" value="${computer.id}" />
							<c:param name="lang" value="fr" />
						</c:url>">
				<img id="fr" src="<c:url value="/resources/img/fr.png" />" width=40 height=20 />
				</a>
		</c:if>
		
		<c:if test="${pageName == 'error'}">
				<a href="<c:url value="${pageContext.request.contextPath}">
							<c:param name="lang" value="en" />
						</c:url>">
				<img id="en" src="<c:url value="/resources/img/en.png" />" width=40 height=20 />
				</a>
				
				<a href="<c:url value="${pageContext.request.contextPath}">
							<c:param name="lang" value="fr" />
						</c:url>">
				<img id="fr" src="<c:url value="/resources/img/fr.png" />" width=40 height=20 />
				</a>
		</c:if>
		
		<c:if test="${pageName == 'login'}">
				<a href="<c:url value="loginPage">
							<c:param name="lang" value="en" />
						</c:url>">
				<img id="en" src="<c:url value="/resources/img/en.png" />" width=40 height=20 />
				</a>
				
				<a href="<c:url value="loginPage">
							<c:param name="lang" value="fr" />
						</c:url>">
				<img id="fr" src="<c:url value="/resources/img/fr.png" />" width=40 height=20 />
				</a>
		</c:if>
		
		</span>
	</div>
</header>