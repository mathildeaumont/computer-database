<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="url" required="true"%>
<%@ attribute name="offset" required="true"%>
<%@ attribute name="nbResults" required="true"%>
<%@ attribute name="order" required="true"%>
<%@ attribute name="search" required="true"%>
<%@ attribute name="direction" required="true"%>
<a
	href="<c:url value="${url}?offset=${offset}&nbResults=${nbResults}&order=${order}&search=${search}&direction=${direction}" />">
	<jsp:doBody />
</a>

