function login() {

	$
			.ajax({
				type : "POST",
				url : contextPath + '/LoginServlet',
				data : {
					'facebook' : 'false',
					'username' : $('#loginUsername').val(),
					'password' : $('#loginPassword').val(),

				},
				success : function(data) {

					var responseJson = eval("(" + data + ")");
					if (responseJson != null) {
						var toAdd = '<li id="userButton" class="dropdown dropdown-avatar"><a id="userButton" data-toggle="dropdown" class=""><span>Benvenuto,&nbsp;';
						toAdd += responseJson["username"];
						toAdd += '<i class="caret"></i></span></a><ul class="dropdown-menu user-login-drop arrow-up"><li><div class="navbar-wrapper">	<div class="navbar-content navbar-avatar"><div class="row"><div class="col-md-5 col-xs-7"><img src="';
						if (responseJson["imageUrl"] != null && responseJson["imageUrl"] != "")
							toAdd += responseJson["imageUrl"];
						else
							toAdd += 'images/profileUnknown.png';
						toAdd += '" width="100" height="100" alt="Avatar utente">	</div><div class="col-md-7 col-xs-5">';
						toAdd += '<span>' + responseJson["username"] + '</span>';
						toAdd += '<p class="text-muted small">'
								+ responseJson["email"]
								+ '</p><div class="divider"></div><a id="controlPanelButton" class="btn btn-success btn-sm btn-block" onclick="loadUserDetails()"><i class="fa fa-user"></i>&nbsp;Pannello di Controllo</a>	</div></div></div><div class="navbar-footer"><div class="navbar-footer-content"><div class="row">	<div class="col-md-6 col-xs-6"></div>	<div class="col-md-6 col-xs-6"><a onclick="logOut()" class="btn btn-default btn-sm pull-right">Logout</a></div></div></div>	</div>	</div>	</li></ul> </li>';

						$('#loginButton').replaceWith(toAdd);
						$('#login').modal('hide');
						reloadSingleDish();
					}

				}
			});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '910265102342864',
		xfbml : true,
		version : 'v2.5'
	});
};
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function loginWithFacebook() {
	FB.login(function(response) {
		if (response.authResponse) {
			getUserInfo();
		} else {
			console.log('User cancelled login or did not fully authorize.');
		}
	}, {
		scope : 'email,user_photos,user_videos'
	});

}

function getUserInfo() {
	FB
			.api(
					'/me',
					{
						fields : [ 'email', 'name', 'picture.type(normal)' ]
					},
					function(response) {

						console.log(response.picture.data.url);
						$
								.ajax({
									type : "POST",
									url : contextPath + '/LoginServlet',
									data : {
										'facebook' : true,
										'id' : response.id,
										'email' : response.email,
										'profile_name' : response.name,
										'image_url' : response.picture.data.url

									},
									success : function(data) {

										// $('#login').modal('hide');
										// alert(data);
										// location.reload(true);
										var responseJson = eval("(" + data + ")");
										if (responseJson != null) {
											var toAdd = '<li id="userButton" class="dropdown dropdown-avatar"><a data-toggle="dropdown" class=""><span>Benvenuto,&nbsp;';
											toAdd += responseJson["profileName"];
											toAdd += '<i class="caret"></i></span></a><ul class="dropdown-menu user-login-drop arrow-up"><li><div class="navbar-wrapper">	<div class="navbar-content navbar-avatar"><div class="row"><div class="col-md-5 col-xs-7"><img src="';
											if (responseJson["imageUrl"] != null && responseJson["imageUrl"] != "")
												toAdd += responseJson["imageUrl"];
											else
												toAdd += 'images/profileUnknown.png';

											toAdd += '" width="100" height="100" alt="Avatar utente">	</div><div class="col-md-7 col-xs-5">';
											toAdd += '<span>' + responseJson["profileName"] + '</span>';
											toAdd += '<p class="text-muted small">'
													+ responseJson["email"]
													+ '</p><div class="divider"></div><a id="controlPanelButton" class="btn btn-success btn-sm btn-block" onclick="loadUserDetails()"><i class="fa fa-user"></i>&nbsp;Pannello di Controllo</a>	</div></div></div><div class="navbar-footer"><div class="navbar-footer-content"><div class="row">	<div class="col-md-6 col-xs-6"></div>	<div class="col-md-6 col-xs-6"><a onclick="logOut()" class="btn btn-default btn-sm pull-right">Logout</a></div></div></div>	</div>	</div>	</li></ul> </li>';

											$('#loginButton').replaceWith(toAdd);
											$('#login').modal('hide');
											reloadSingleDish();
										}
									}
								});

					});
}

function loadUserDetails() {

	$("#mainIndex").load("userProfile.jsp");

}

function reloadSingleDish() {
	if ($("#singleDish").length) {
		var dishID = $('.box10').attr('id');
		showDish(dishID);
	}

}
