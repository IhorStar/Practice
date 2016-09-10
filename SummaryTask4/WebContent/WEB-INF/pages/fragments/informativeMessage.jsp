<%@ include file="/WEB-INF/pages/fragments/head.jsp" %>
<c:set var="message" value="${error}"></c:set>
<c:choose>
	<c:when test="${success != null}">
		<div class="successMessage">
			<fmt:message key="${success}"/>
		</div>
	</c:when>
	<c:when test="${message != null}">
		<div class="errorMessage">
			<fmt:message key="${message}"/>
		</div>
	</c:when>
</c:choose>
<c:remove var="success" scope="session"/>