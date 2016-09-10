<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="/WEB-INF/pages/teacher/fragments/teacherLeftMenu.jsp"></jsp:include>
			<c:set var="courseDAO" value="${courseDAO}"></c:set>
			<c:set var="userDAO" value="${userDAO}"></c:set>
			<c:set var="journalDAO" value="${journalDAO}"></c:set>
			<c:forEach items="${allTeacherCourses}" var="teacherCourseId">
				<div class="col-xs-12 col-sm-8 col-md-9 pull-right">
					<div class="panel panel-default">
						<div class="panel-heading">${courseDAO.getCourseById(teacherCourseId).getName()}</div>
						<div class="panel-body">
							<table class="table table-striped">
							    <thead>
							      <tr>
							        <th><fmt:message key="First.Name"/></th>
							        <th><fmt:message key="Last.Name"/></th>
							        <th><fmt:message key="Rating"/></th>
							        <th></th>
							      </tr>
							    </thead>
							    <tbody>
									<c:forEach items="${journalDAO.getAllStudentsIdByCourseId(teacherCourseId)}" var="studentId">
										<c:set var="student" value="${userDAO.getUserById(studentId)}"></c:set>
										<form role="form" id="updateStudentRating" class="col-md-9 form-horizontal" action="/SummaryTask4/updateRatingServlet">
											<tr>
									    		<td>${student.getFirstName()}</td>
									    		<td>${student.getLastName()}</td>
							    				<td class="col-md-2">
							    					<input type="text" name="rating" id="rating" class="form-control input-sm col-md-3" value="${journalDAO.getRatingByStudentIdAndCourseId(studentId, teacherCourseId)}">
							    				</td>
							    				<td class="col-md-3">
								    				<input type="hidden" name="studentId" value="${studentId}">
								    				<input type="hidden" name="courseId" value="${teacherCourseId}">
								    				<input type="submit" value="<fmt:message key="Update.rating"/>" class="btn btn-success btn-block">
							    				</td>
											</tr>
							    		</form>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>