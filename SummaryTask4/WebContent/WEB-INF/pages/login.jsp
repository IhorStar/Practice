<%@ include file="/WEB-INF/pages/fragments/head.jsp" %>
<jsp:include page="/WEB-INF/pages/fragments/navbar.jsp"></jsp:include>
<div class="container">

	<div class="row centered-form">
        <div class="col-xs-8 col-sm-6 col-md-5 col-lg-4 col-sm-offset-3 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
	    			<h3 class="panel-title"><fmt:message key="Please.log.in"/></h3>
	 			</div>
	 			<div class="panel-body">
		    		<form role="form" id="loginForm" action="loginServlet" method="post">
		    			<c:set var="message" value="${error}"></c:set>
						<c:if test="${message != null}">
							<div class="errorMessage">
			    				<fmt:message key="${message}"/>
			    			</div>
						</c:if>

		    			<div class="form-group">
		    				<input type="email" name="email" id="email" class="form-control input-sm" placeholder="<fmt:message key="Email.Address"/>">
		    			</div>
    					<div class="form-group">
    						<input type="password" name="password" id="password" class="form-control input-sm" placeholder="<fmt:message key="Password"/>">
    					</div>
		    			<input type="submit" value="<fmt:message key="Log.In"/>" class="btn btn-success btn-block">
		    		</form>
	    		</div>
	    	</div>
    	</div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/fragments/loginFormValidator.jsp" %>
<jsp:include page="/WEB-INF/pages/fragments/footer.jsp"></jsp:include>