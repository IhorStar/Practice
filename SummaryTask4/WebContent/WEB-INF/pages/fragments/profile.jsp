<%@ include file="/WEB-INF/pages/fragments/languageSelectorSetting.jsp" %>
<div class="col-sm-8 col-md-9">
	<jsp:include page="/WEB-INF/pages/fragments/informativeMessage.jsp"></jsp:include>
	<div class="panel panel-default">
		<div class="panel-heading"><fmt:message key="Profile"/></div>
		<div class="panel-body">
			<form role="form" id="userInfo" class="col-md-9 form-horizontal" action="update" method="post">
				<div class="form-group">
					<div class="col-md-6">
						<b><fmt:message key="Email"/></b>
						<input type="email" name="email" id="email" class="form-control input-sm" value="${user.getEmail()}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<b><fmt:message key="First.Name"/></b>
						<input type="text" name="firstName" id="firstName" class="form-control input-sm" value="${user.getFirstName()}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<b><fmt:message key="Last.Name"/></b>
						<input type="text" name="lastName" id="lastName" class="form-control input-sm" value="${user.getLastName()}">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>