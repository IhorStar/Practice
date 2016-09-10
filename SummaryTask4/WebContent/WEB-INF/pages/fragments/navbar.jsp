<%@ include file="/WEB-INF/pages/fragments/head.jsp" %>

<div class="navbar navbar-default"> 
	<div class="container">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/SummaryTask4">SummaryTask4</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/SummaryTask4/catalogue"><fmt:message key='Catalogue'/></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:set var="user" value="${sessionScope.user}"></c:set>
				<c:set var="adminRoleId" value="1"></c:set>
				<c:set var="teacherRoleId" value="2"></c:set>
				<c:set var="studentRoleId" value="3"></c:set>
				<c:choose>
					<c:when test="${user == null}">
						<li><a href="/SummaryTask4/registration"><span class="glyphicon glyphicon-user"></span> <fmt:message key="Sign.Up"/></a></li>
						<li><a href="/SummaryTask4/login"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="Log.In"/></a></li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${user.getRoleId() == adminRoleId}">
								<li><a href="/SummaryTask4/admin"><span class="glyphicon glyphicon-home"></span> ${user.getFirstName()}</a></li>
							</c:when>
							<c:when test="${user.getRoleId() == teacherRoleId}">
								<li><a href="/SummaryTask4/teacher"><span class="glyphicon glyphicon-home"></span> ${user.getFirstName()}</a></li>
							</c:when>
							<c:when test="${user.getRoleId() == studentRoleId}">
								<li><a href="/SummaryTask4/student"><span class="glyphicon glyphicon-home"></span> ${user.getFirstName()}</a></li>
							</c:when>
						</c:choose>
						<li><a href="/SummaryTask4/logoutServlet"><span class="glyphicon glyphicon-log-out"></span> <fmt:message key="Log.out"/></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
	
			<div class="nav navbar-nav navbar-right language-selector">
				<form role="form" class="form-horizontal">
		            <select class="form-control" id="language" name="language" onchange="submit()">
		                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
		            </select>
	        	</form>
			</div>
	
		</div>
	</div>
</div>
