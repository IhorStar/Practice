<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-3">
	<div class="panel panel-default">
	 	<div class="panel-body">
		 	<form role="form" action="/SummaryTask4/sortCourseServlet" method="post">
				<label for="sortBy">Sort by</label>
				<select class="form-control" name="sortBy" id="sortBy" onchange="this.form.submit()">
					<option disabled selected value> -- select an option -- </option>
					<option value="asc">Ascending</option>
					<option value="desc">Descending</option>
					<option value="duration">Duration</option>
					<option value="numberStudents">Number of students</option>
				</select>
			</form>
			<form role="form" action="/SummaryTask4/selectCourseBySubjectServlet" method="post">
				<label for="sortBy">Select subject</label>
				<select class="form-control" name="selectBySubject" id="selectBySubject" onchange="this.form.submit()">
					<option disabled selected value> -- select an option -- </option>
					<c:forEach items="${allSubjects}" var="subject">
						<option value="${subject.getId()}">${subject.getName()}</option>
					</c:forEach>
				</select>
			</form>
			<form role="form" action="/SummaryTask4/selectCourseByTeacherServlet" method="post">
				<label for="sortBy">Select teacher</label>
				<select class="form-control" name="selectByTeacher" id="selectByTeacher" onchange="this.form.submit()">
					<option disabled selected value> -- select an option -- </option>
					<c:forEach items="${allTeachers}" var="teacher">
						<option value="${teacher.getId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
					</c:forEach>
				</select>
			</form>
	 	</div>
	</div>
</div>