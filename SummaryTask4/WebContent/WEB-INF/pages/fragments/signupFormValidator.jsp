<%@ include file="/WEB-INF/pages/fragments/languageSelectorSetting.jsp" %>

<script>
	$(document).ready(function() {
		$("#signupForm, #addTeacherForm, #updateUserInfo").validate({
			rules: {
				firstName: "required",
				lastName: "required",
				email: {
					required: true,
					email: true
				},
				password: {
					required: true,
					minlength: 6
				},
				passwordConfirmation: {
					required: true,
					minlength: 6,
					equalTo: "#password"
				}
			},
			messages: {
				firstName: "<fmt:message key='Please.enter.your.first.name'/>",
	            lastName: "<fmt:message key='Please.enter.your.last.name'/>",
	            email: {
	            	required: "<fmt:message key='Please.enter.your.email'/>",
	            	email: "<fmt:message key='Please.enter.a.valid.email.address'/>"
	            },
	            password: {
	                required: "<fmt:message key='Please.provide.a.password'/>",
	                minlength: "<fmt:message key='Your.password.must.be.at.least.6.characters.long'/>"
	            },
	            passwordConfirmation: {
					required: "<fmt:message key='Please.provide.a.password'/>",
					minlength: "<fmt:message key='Your.password.must.be.at.least.6.characters.long'/>",
					equalTo: "Please enter the same password as above"
				}
			}
		});
	});
</script>