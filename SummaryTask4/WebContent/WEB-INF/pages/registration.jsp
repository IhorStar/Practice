<%@ include file="/WEB-INF/pages/fragments/navbar.jsp" %>

<div class="container">

	<div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-5 col-sm-offset-2 col-md-offset-3">
        	<div class="panel panel-default">
        		<div class="panel-heading">
	    			<h3 class="panel-title"><fmt:message key="Please.sign.up"/></h3>
	 			</div>
	 			<div class="panel-body">
		    		<form role="form" id="signupForm" action="registrationServlet" method="post">
		    			<c:set var="message" value="${error}"></c:set>
		    			<c:choose>
		    				<c:when test="${success != null}">
		    					<div class="successMessage">
				    				<fmt:message key="${success}"/>
				    			</div>
		    				</c:when>
							<c:when test="${message != null}">
								<div class="errorMessage">
			    					<fmt:message key="${message}"/>
			    				</div>
							</c:when>
		    			</c:choose>
		    			<c:remove var="success" scope="session"/>

		    			<div class="row">
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		                			<input type="text" name="firstName" id="firstName" class="form-control input-sm" placeholder="<fmt:message key="First.Name"/>">
		    					</div>
		    				</div>
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		    						<input type="text" name="lastName" id="lastName" class="form-control input-sm" placeholder="<fmt:message key="Last.Name"/>">
		    					</div>
		    				</div>
		    			</div>

		    			<div class="form-group">
		    				<input type="email" name="email" id="email" class="form-control input-sm" placeholder="<fmt:message key="Email.Address"/>">
		    			</div>

		    			<div class="row">
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		    						<input type="password" name="password" id="password" class="form-control input-sm" placeholder="<fmt:message key="Password"/>">
		    					</div>
		    				</div>
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		    						<input type="password" name="passwordConfirmation" id="passwordConfirmation" class="form-control input-sm" placeholder="<fmt:message key="Confirm.Password"/>">
		    					</div>
		    				</div>
		    			</div>

		    			<input type="submit" value="<fmt:message key="Sign.Up"/>" class="btn btn-success btn-block">

		    		</form>
	    		</div>
	    	</div>
    	</div>
	</div>

</div>
<%@ include file="/WEB-INF/pages/fragments/signupFormValidator.jsp" %>
<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>
