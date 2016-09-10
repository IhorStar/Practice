<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">
	<div class="row">
		<div class="container">

			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="panel panel-default">
				 	<div class="panel-body">
					 	<form role="form" action="/SummaryTask4/sortCourseServlet" method="post">
							<label for="sortBy"><fmt:message key="Sort.by"/></label>
							<select class="form-control" name="sortBy" id="sortBy" onchange="this.form.submit()">
								<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
								<option value="asc"><fmt:message key="Ascending"/></option>
								<option value="desc"><fmt:message key="Descending"/></option>
								<option value="duration"><fmt:message key="Duration"/></option>
								<option value="numberStudents"><fmt:message key="Number.of.students"/></option>
							</select>
						</form>
						<form role="form" action="/SummaryTask4/selectCourseBySubjectServlet" method="post">
							<label for="sortBy"><fmt:message key="Select.subject"/></label>
							<select class="form-control" name="selectBySubject" id="selectBySubject" onchange="this.form.submit()">
								<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
								<c:forEach items="${allSubjects}" var="subject">
									<option value="${subject.getId()}">${subject.getName()}</option>
								</c:forEach>
							</select>
						</form>
						<form role="form" action="/SummaryTask4/selectCourseByTeacherServlet" method="post">
							<label for="sortBy"><fmt:message key="Select.teacher"/></label>
							<select class="form-control" name="selectByTeacher" id="selectByTeacher" onchange="this.form.submit()">
								<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
								<c:forEach items="${allTeachers}" var="teacher">
									<option value="${teacher.getId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
								</c:forEach>
							</select>
						</form>
				 	</div>
				</div>
			</div>
			<c:set var="userDAO" value="${userDAO}"></c:set>
			<c:set var="journalDAO" value="${journalDAO}"></c:set>
			<c:forEach items="${allCourses}" var="course">
				<c:set var="teacher" value="${userDAO.getUserById(course.getTeacherId())}"></c:set>
				<div class="col-xs-12 col-sm-8 col-md-9 pull-right">
					<div class="panel panel-info">
						<div class="panel-heading">${course.getName()}</div>
						<div class="panel-body">
							<div class="col-md-6"><fmt:message key="Course.start.date"/>: ${course.getCourseStartDate()}</div>
							<div class="col-md-6"><fmt:message key="Course.duration"/>: ${course.getDuration()} <fmt:message key="hours"/></div>
							<div class="col-md-6"><fmt:message key="Course.end.date"/>: ${course.getCourseEndDate()}</div>
							<div class="col-md-6"><fmt:message key="Teacher"/>: ${teacher.getFirstName()} ${teacher.getLastName()}</div>
							<div class="col-md-6"><fmt:message key="Number.of.students"/>: ${course.getNumberStudents()}</div>
							<div class="col-xs-6 col-sm-8 col-md-6 col-md-offset-2">
								<form role="form" class="" id="registerForCourseForm" action="/SummaryTask4/registerForCourseServlet" method="post">
									<input type="hidden" name="courseId" value="${course.getId()}">
									<input type="hidden" name="teacherId" value="${course.getTeacherId()}">
									<input type="hidden" name="studentId" value="${user.getId()}">
									<c:set var="user" value="${user}"></c:set>
									<c:set var="studentRoleId" value="3"></c:set>
									<c:set var="journal" value="${journalDAO.getJournalByStudentIdAndCourseId(user.getId(), course.getId())}"></c:set>
									<c:choose>
										<c:when test="${user.getRoleId() == studentRoleId && currentDate.before(course.getCourseStartDate()) && journal.getCourseId() == 0 && journal.getStudentId() == 0}">
											<input type="submit" value="<fmt:message key="Register.for.course"/>" class="btn btn-success btn-block">
										</c:when>
										<c:when test="${user == null}">
											<a class="btn btn-success btn-block" href="/SummaryTask4/login"><fmt:message key="Log.in.to.register.for.the.course"/></a>
										</c:when>
									</c:choose>
								</form>
							</div>
						</div> 
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>