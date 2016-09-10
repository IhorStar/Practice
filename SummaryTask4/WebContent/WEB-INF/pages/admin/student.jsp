<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="/WEB-INF/pages/admin/fragments/adminLeftMenu.jsp"></jsp:include>

			<div class="col-sm-8 col-md-9">
				<jsp:include page="/WEB-INF/pages/fragments/informativeMessage.jsp"></jsp:include>
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Block.student"/></div>
					<div class="panel-body">
						<form role="form" id="userInfo" class="col-md-9 form-horizontal" action="/SummaryTask4/blockUserServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<select class="form-control" name="activeUser" id="activeUser">
										<c:forEach items="${allActiveStudents}" var="activeStudent">
											<option value="${activeStudent.getId()}">${activeStudent.getFirstName()} ${activeStudent.getLastName()} (${activeStudent.getEmail()})</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Block.student"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-8 col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Unblock.student"/></div>
					<div class="panel-body">
						<form role="form" id="userInfo" class="col-md-9 form-horizontal" action="/SummaryTask4/unblockUserServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<select class="form-control" name="blockUser" id="blockUser">
										<c:forEach items="${allBlockedStudents}" var="blockedStudent">
											<option value="${blockedStudent.getId()}">${blockedStudent.getFirstName()} ${blockedStudent.getLastName()} (${blockedStudent.getEmail()})</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Unblock.student"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>