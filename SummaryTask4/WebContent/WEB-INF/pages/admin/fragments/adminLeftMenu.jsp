<%@ include file="/WEB-INF/pages/fragments/languageSelectorSetting.jsp" %>
<div class="col-sm-4 col-md-3">
	<div class="panel panel-default">
	 	<div class="panel-heading"><fmt:message key="Personal.settings"/></div>
		<div class="panel-body left-menu-panel-body">
			<ul class="nav nav-sidebar" id="sidebar">
				<li><a href="/SummaryTask4/admin"><fmt:message key="Profile"/></a></li>
				<li><a href="/SummaryTask4/admin/update"><fmt:message key="Update.profile"/></a></li>
				<li><a href="/SummaryTask4/admin/addTeacher"><fmt:message key="Teacher"/></a></li>
				<li><a href="/SummaryTask4/admin/course"><fmt:message key="Course"/></a></li>
				<li><a href="/SummaryTask4/admin/student"><fmt:message key="Student"/></a></li>
			</ul>
		</div>
	</div>
</div>