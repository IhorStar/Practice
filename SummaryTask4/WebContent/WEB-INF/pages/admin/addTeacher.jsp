<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">

	<div class="row">
		<div class="container">
			<jsp:include page="/WEB-INF/pages/admin/fragments/adminLeftMenu.jsp"></jsp:include>

			<div class="col-sm-8 col-md-9">
				<jsp:include page="/WEB-INF/pages/fragments/informativeMessage.jsp"></jsp:include>
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Add.teacher"/></div>
					<div class="panel-body">
						<form role="form" id="addTeacherForm" class="col-md-9 form-horizontal" action="/SummaryTask4/addTeacherServlet" method="post">
							<div style="color: #FF0000;">${errorMessage}</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="email" name="email" id="email" class="form-control input-sm" placeholder="<fmt:message key="Teacher.Email.Address"/>">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="text" name="firstName" id="firstName" class="form-control input-sm" placeholder="<fmt:message key="First.Name"/>">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="text" name="lastName" id="lastName" class="form-control input-sm" placeholder="<fmt:message key="Last.Name"/>">
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
									<input type="submit" value="<fmt:message key="Add.teacher"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/fragments/signupFormValidator.jsp" %>
<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>