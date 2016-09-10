<form role="form" id="updateUserInfo" class="col-md-9 form-horizontal" action="/SummaryTask4/updateUserInfoServlet" method="post">
	<div style="color: #FF0000;">${errorMessage}</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="email" name="email" id="email" class="form-control input-sm" value="${user.getEmail()}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="text" name="firstName" id="firstName" class="form-control input-sm" value="${user.getFirstName()}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="text" name="lastName" id="lastName" class="form-control input-sm" value="${user.getLastName()}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="password" name="passwordConfirmation" id="passwordConfirmation" class="form-control input-sm" placeholder="Confirm Password">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-6">
			<input type="submit" value="Update" class="btn btn-success btn-block">
		</div>
	</div>
</form>