<%@ include file="/WEB-INF/pages/fragments/languageSelectorSetting.jsp" %>

<script>
	$(document).ready(function() {
		$("#loginForm").validate( {
			rules: {
				email: {
					required: true,
					email: true
				},
				password: {
					required: true,
					minlength: 6
				},
			},
			messages: {
				email: {
					required: "<fmt:message key='Please.enter.your.email'/>",
	            	email: "<fmt:message key='Please.enter.a.valid.email.address'/>"
				},
				password: {
	                required: "<fmt:message key='Please.provide.a.password'/>",
	                minlength: "<fmt:message key='Your.password.must.be.at.least.6.characters.long'/>"
	            }
			}
		});
	});
</script>