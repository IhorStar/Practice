<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="/WEB-INF/pages/student/fragments/studentLeftMenu.jsp"></jsp:include>
			<c:set var="courseDAO" value="${courseDAO}"></c:set>
			<c:set var="userDAO" value="${userDAO}"></c:set>
			<div class="col-sm-8 col-md-9 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Courses.that.have.not.yet.started"/></div>
					<table class="table table-striped">
					    <thead>
					      <tr>
					        <th class="col-md-4"><fmt:message key="Course.Name"/></th>
					        <th class="col-md-4"><fmt:message key="Teacher"/></th>
					        <th class="col-md-4"><fmt:message key="Course.start.date"/></th>
					      </tr>
					    </thead>
					    <tbody>
							<c:forEach items="${allJournals}" var="journal">
								<c:set var="course" value="${courseDAO.getCourseById(journal.getCourseId())}"></c:set>
								<c:set var="user" value="${userDAO.getUserById(journal.getTeacherId())}"></c:set>
								<c:if test="${currentDate.before(course.getCourseStartDate())}">
									<tr>
										<td>${course.getName()}</td>
										<td>${user.getFirstName()} ${user.getLastName()}</td>
										<td>${course.getCourseStartDate()}</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-8 col-md-9 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Courses.that.are.in.progress"/></div>
					<table class="table table-striped">
					    <thead>
					      <tr>
					        <th class="col-md-4"><fmt:message key="Course.Name"/></th>
					        <th class="col-md-4"><fmt:message key="Teacher"/></th>
					        <th class="col-md-4"><fmt:message key="Course.end.date"/></th>
					      </tr>
					    </thead>
					    <tbody>
							<c:forEach items="${allJournals}" var="journal">
								<c:set var="course" value="${courseDAO.getCourseById(journal.getCourseId())}"></c:set>
								<c:set var="user" value="${userDAO.getUserById(journal.getTeacherId())}"></c:set>
								<c:if test="${currentDate.after(course.getCourseStartDate()) && currentDate.before(course.getCourseEndDate())}">
								<tr>
									<td>${course.getName()}</td>
									<td>${user.getFirstName()} ${user.getLastName()}</td>
									<td>${course.getCourseEndDate()}</td>
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-8 col-md-9 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Courses.that.are.completed"/></div>
					<table class="table table-striped">
						<thead>
					      <tr>
					        <th class="col-md-4"><fmt:message key="Course.Name"/></th>
					        <th class="col-md-4"><fmt:message key="Teacher"/></th>
					        <th class="col-md-4"><fmt:message key="Rating"/></th>
					      </tr>
					    </thead>
					    <tbody>
							<c:forEach items="${allJournals}" var="journal">
								<c:set var="course" value="${courseDAO.getCourseById(journal.getCourseId())}"></c:set>
								<c:set var="user" value="${userDAO.getUserById(journal.getTeacherId())}"></c:set>
								<c:if test="${currentDate.after(course.getCourseEndDate())}">
									<tr>
										<td>${course.getName()}</td>
										<td>${user.getFirstName()} ${user.getLastName()}</td>
										<td>${journal.getRating()}</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>