<%@ include file="/WEB-INF/pages/fragments/languageSelectorSetting.jsp" %>
<div class="col-sm-8 col-md-9">
	<jsp:include page="/WEB-INF/pages/fragments/informativeMessage.jsp"></jsp:include>
	<div class="panel panel-default">
		<div class="panel-heading"><fmt:message key="Update.profile"/></div>
		<div class="panel-body">
			<form role="form" id="updateUserInfo" class="col-md-9 form-horizontal" action="/SummaryTask4/updateUserInfoServlet" method="post">
				<div class="form-group">
					<div class="col-md-6">
						<input type="email" name="email" id="email" class="form-control input-sm" placeholder="<fmt:message key="Email.Address"/>" value="${user.getEmail()}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="text" name="firstName" id="firstName" class="form-control input-sm" placeholder="<fmt:message key="First.Name"/>" value="${user.getFirstName()}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="text" name="lastName" id="lastName" class="form-control input-sm" placeholder="<fmt:message key="Last.Name"/>" value="${user.getLastName()}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="password" name="password" id="password" class="form-control input-sm" placeholder="<fmt:message key="Password"/>">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="password" name="passwordConfirmation" id="passwordConfirmation" class="form-control input-sm" placeholder="<fmt:message key="Confirm.Password"/>">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6">
						<input type="submit" value="<fmt:message key="Update"/>" class="btn btn-success btn-block">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/fragments/signupFormValidator.jsp" %>