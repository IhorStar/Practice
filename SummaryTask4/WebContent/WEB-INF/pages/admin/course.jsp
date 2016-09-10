<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">

	<div class="row">
		<div class="container">
			<jsp:include page="/WEB-INF/pages/admin/fragments/adminLeftMenu.jsp"></jsp:include>

			<div class="col-sm-8 col-md-9">
				<jsp:include page="/WEB-INF/pages/fragments/informativeMessage.jsp"></jsp:include>
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Add.subject"/></div>
					<div class="panel-body">
						<form role="form" id="addSubjectForm" class="col-md-9 form-horizontal" action="/SummaryTask4/addSubjectServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Subject.Name"/></b>
									<input type="text" name="subject" id="subject" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Add.subject"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-8 col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Add.course"/></div>
				    <div class="panel-body">
					    <form role="form" id="addCourseForm" class="col-md-9 form-horizontal" action="/SummaryTask4/addCourseServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Course.Name"/></b>
									<input type="text" name="courseName" id="courseName" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<label for="subjectName"><fmt:message key="Subject.Name"/></label>
									<select class="form-control" name="subjectName" id="subjectName" required>
										<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
										<c:forEach items="${allSubjects}" var="subject">
											<option value="${subject.getId()}">${subject.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6 date">
									<b><fmt:message key="Course.start.date"/></b>
									<input type="text" name="courseStartDate" id="courseStartDate" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Course.end.date"/></b>
									<input type="text" name="courseEndDate" id="courseEndDate" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Course.Duration.hours"/></b>
									<input type="text" name="courseDuration" id="courseDuration" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<label for="courseTeacher"><fmt:message key="Course.Teacher"/></label>
									<select class="form-control" name="courseTeacher" id="courseTeacher" required>
										<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
										<c:forEach items="${allTeachers}" var="teacher">
											<option value="${teacher.getId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Add.course"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
			    </div>
			</div>
			<div class="col-sm-8 col-md-9 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Update.course"/></div>
				    <div class="panel-body">
					    <form role="form" id="updateCourseForm" class="col-md-9 form-horizontal" action="/SummaryTask4/updateCourseServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<label for="courseName"><fmt:message key="Select.course.which.you.want.to.update"/></label>
									<select class="form-control" name="courseName" id="courseName" required>
										<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
										<c:forEach items="${allCourses}" var="course">
											<option value="${course.getId()}">${course.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6 date">
									<b><fmt:message key="Course.start.date"/></b>
									<input type="text" name="courseStartDate" id="courseStartDate" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Course.end.date"/></b>
									<input type="text" name="courseEndDate" id="courseEndDate" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<b><fmt:message key="Course.Duration.hours"/></b>
									<input type="text" name="courseDuration" id="courseDuration" class="form-control input-sm" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<label for="courseTeacher"><fmt:message key="Course.Teacher"/></label>
									<select class="form-control" name="courseTeacher" id="courseTeacher" required>
										<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
										<c:forEach items="${allTeachers}" var="teacher">
											<option value="${teacher.getId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Update.course"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
			    </div>
			</div>
			<div class="col-sm-8 col-md-9 pull-right">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="Delete.course"/></div>
				    <div class="panel-body">
					    <form role="form" id="updateCourseForm" class="col-md-9 form-horizontal" action="/SummaryTask4/deleteCourseServlet" method="post">
							<div class="form-group">
								<div class="col-md-6">
									<label for="courseName"><fmt:message key="Select.course.which.you.want.to.delete"/></label>
									<select class="form-control" name="courseName" id="courseName" required>
										<option disabled selected value> -- <fmt:message key="select.an.option"/> -- </option>
										<c:forEach items="${allCourses}" var="course">
											<option value="${course.getId()}">${course.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<input type="submit" value="<fmt:message key="Delete.course"/>" class="btn btn-success btn-block">
								</div>
							</div>
						</form>
					</div>
			    </div>
			</div>
			<script>
			  	$(function() {
				    $("#courseStartDate, #courseEndDate").datepicker({
				    	format: "dd-mm-yyyy"
				    });
				});
		  	</script>
		 </div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>