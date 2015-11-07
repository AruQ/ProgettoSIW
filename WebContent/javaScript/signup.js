function submitSignup() {
	$.ajax({
		type : "POST",
		url : contextPath + '/Signup',
		data : {
			'username' : $('#usernameSignup').val(),
			'password' : $('#passwordSignup').val(),
			'email' : $("#mailSignup").val()
		},
		success : function(data) {
			console.log("sono in submit Signup");
			$('#signup').modal('hide');
		}
	});
}

function checkInputs() {

	$.ajax({
		type : "POST",
		url : contextPath + '/CheckSignup',
		data : {
			'username' : $('#usernameSignup').val(),
			'email' : $("#mailSignup").val()
		},
		success : function(data) {
			var responseJson = eval('(' + data + ')');

			var validPassword = false;
			var responseUsername = responseJson["username"];
			var responseEmail = responseJson["email"];
			if (responseUsername === "empty") {
				$("#usernameSignup").removeClass($('#usernameSignup').attr('class')).addClass("decoratedNormalForm");
			}
			if (responseUsername === "true") {
				$("#usernameSignup").removeClass($('#usernameSignup').attr('class')).addClass("decoratedCheckedForm");

			}
			if (responseUsername === "false") {

				$("#usernameSignup").removeClass($('#usernameSignup').attr('class')).addClass("decoratedErrorForm");
			}

			if (responseEmail === "empty") {

				$("#mailSignup").removeClass($('#mailSignup').attr('class')).addClass("decoratedNormalForm");
			}
			if (responseEmail === "true") {

				$("#mailSignup").removeClass($('#mailSignup').attr('class')).addClass("decoratedCheckedForm");

			}
			if (responseEmail === "false") {
				$("#mailSignup").removeClass($('#mailSignup').attr('class')).addClass("decoratedErrorForm");

			}

			if ($("#passwordSignup").val().length > 6)
				validPassword = true;

			if (validPassword) {
				$("#passwordSignup").removeClass($('#passwordSignup').attr('class')).addClass("decoratedCheckedForm");
			} else {
				if ($("#passwordSignup").val().length == 0) {
					$("#passwordSignup").removeClass($('#passwordSignup').attr('class')).addClass("decoratedNormalForm");

				} else {
					$("#passwordSignup").removeClass($('#passwordSignup').attr('class')).addClass("decoratedErrorForm");
				}

			}
			if (responseUsername === "true" && responseEmail === "true" && validPassword == true) {
				$("#submitSignup").prop("disabled", false);
			} else {
				$("#submitSignup").prop("disabled", true);

			}

		},
	});
}
