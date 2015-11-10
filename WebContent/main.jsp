<%@page import="project.beans.User"%>
<%@page import="com.fasterxml.jackson.databind.*"%>
<%@page import="project.database.*, java.text.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	var contextPath ='<%=request.getContextPath()%>';
</script>

<style type="text/css">
@import url(http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
</style>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js"></script> -->
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/signup.css" rel="stylesheet" type="text/css" />
<link href="css/message.css" rel="stylesheet" type="text/css" />
<script src="javaScript/login.js" type="text/javascript"></script>
<script src="javaScript/signup.js" type="text/javascript"></script>
<script src="javaScript/index.js" type="text/javascript"></script>
<script src="javaScript/map.js" type="text/javascript"></script>
<script src="javaScript/message.js" type="text/javascript"></script>






</head>
<body>
	<div class="error message loginError">
		<h3>Error</h3>
		<p>Username o password errati</p>
	</div>
		<div class="error message signUpError">
		<h3>Error</h3>
		<p>Inserisci prima tutti i campi</p>
	</div>


	<div class="success message loginSuccess">
		<h3>Login avvenuto con successo</h3>

	</div>
	
		<div class="success message rateAdded">
		<h3>Valutazione aggiunta con successo</h3>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var myLoadedData = localStorage["user"];
			console.log(myLoadedData);

			$("#mainIndex").load("home.jsp");

			loadPositions();
		 	if (myLoadedData != null && myLoadedData != "null") {
				console.log(myLoadedData);
				myLoadedData = eval("(" + myLoadedData + ")");
				addUserPanel(myLoadedData["username"], myLoadedData["profileName"], myLoadedData["imageUrl"], myLoadedData["social"], myLoadedData["email"]);
				addToSession(myLoadedData);

			}
		});
		function logOut() {
	<%session.setAttribute("user", null);%>
		localStorage["user"] = null;
			$("#userButton").replaceWith('<li id ="loginButton" data-toggle="modal" data-target="#login"><a href="#">LOG IN</a></li>');
			document.location.reload();

		}
		function loadHome() {

			document.location.reload();

		}
	</script>


	<%
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//get current date time with Date()
		Date date = new Date();

		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		String currentData = dateFormat.format(cal.getTime());
	%>


	<header role="banner" id="top" style="background: #ffffff url('images/red-area2.png') repeat; background-repeat: repeat-x;background-size: auto 100%;height:150px;"
		class="navbar navbar-inverse navbar-static-top flat-nav navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button data-target=".flat-nav-collapse" data-toggle="collapse" type="button" class="nav-tog lines-button x navbar-toggle neutro">
				<span class="sr-only">Togli Navigazione</span> <span class="lines"></span>
			</button>
			<a class="navbar-brand" onclick="loadHome"><img style="width: 300px;" src="./images/logo_unical.png" class="img-responsive" alt="Forum Logo"></a>
		</div>
		<!-- Main navigation block -->
		<nav class="collapse navbar-collapse flat-nav-collapse" role="navigation" aria-label="Primary">
		<ul id="navbarul" class="nav navbar-nav navbar-nav-fancy" style="color: blue;">
			<li><a onclick="loadHome()"><i class="fa fa-home fa-lg"></i> HOME</a></li>
			<li><a onclick="loadMenu('<%=currentData%>','dd-MM-yyyy')"><i class="fa fa-calendar fa-lg"></i> MENU</a><span></span></li>
			<li><a onclick="goToMap()"><i class="fa fa-location-arrow fa-lg"></i> LOCATION</a><span></span></li>
			<li><a onclick="loadDishes()"><i class="fa fa-cutlery fa-lg"></i> DISHES</a><span></span></li>

		</ul>

		<!-- 
		<div class="user-login hidden-xs">
 <ul class="nav navbar-nav navbar-right navbar-nav-fancy">
 <li id="userButton" class="dropdown dropdown-avatar"><a data-toggle="dropdown" class=""><span>Benvenuto,&nbsp;Blue Rose<i class="caret"></i></span></a>
 <ul class="dropdown-menu user-login-drop arrow-up">
 <li><a href="./ucp.php?i=pm&amp;folder=inbox"> Hai <strong>0</strong> nuovi messaggi privati
 </a></li>
 <li>
 <div class="navbar-wrapper">
 <div class="navbar-content navbar-avatar">
 <div class="row">
 <div class="col-md-5 col-xs-7">
 <img src="http://i262.photobucket.com/albums/ii103/Paolina93/Altro/33_zps292be479.jpg" width="100" height="100" alt="Avatar utente">
 <p class="text-center">
 <a class="" href="./ucp.php?i=profile&amp;mode=avatar"> <small>Cambia Avatar</small>
 </a>
 </p>
 </div>
 <div class="col-md-7 col-xs-5">
 <span>Blue Rose</span>

 <p class="text-muted small">tsunade_93@hotmail.it</p>
 <div class="divider"></div>
 <a class="btn btn-success btn-sm btn-block" href="./ucp.php"><i class="fa fa-user"></i>&nbsp;Pannello di Controllo</a>
 </div>
 </div>
 </div>
 <div class="navbar-footer">
 <div class="navbar-footer-content">
 <div class="row">
 <div class="col-md-6 col-xs-6">
 <a class="btn btn-default btn-sm" href="./ucp.php?i=profile&amp;mode=reg_details">Cambia Password</a>
 </div>
 <div class="col-md-6 col-xs-6">
 <a class="btn btn-default btn-sm pull-right" href="./ucp.php?mode=logout&amp;sid=7d4d7e8d35445e440d1717f033916229">Logout</a>
 </div>
 </div>
 </div>
 </div>
 </div>
 </li>
 </ul></li>
 </ul>
 </div> -->


		<div class="user-login hidden-xs">
			<ul id="navbarul" class="nav navbar-nav navbar-right navbar-nav-fancy">
				<li id="loginButton" data-dismiss="modal" data-toggle="modal" data-target="#login"><a>LOG IN</a></li>

			</ul>
		</div>

		</nav>
	</div>

	</header>






	<!-- <div id="background">
	</div> -->
	<div class="row" style="margin-top: 150px; background-color: white""></div>
	<div class="row">

		<div class="col-lg-2"></div>
		<div class="col-lg-8" id="mainIndex"></div>
		<div class="col-lg-2"></div>
	</div>


	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg wrap" id="login" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">

						<span aria-hidden="true">&times;</span>
					</button>


					<div class="modal-body">


						<div class="contact-form">

							<!-- start-form -->
							<form class="contact_form" action="#" method="post" name="contact_form">
								<h10>Login Into Your Account</h10>
								<ul>

									<li><input type="text" class="textbox2" name="text" placeholder="Username" id="loginUsername" required /> <span class="form_hint">Enter a valid username</span>
										<p>
											<img src="images/contact.png" alt="">
										</p></li>
									<li><input type="password" name="website" class="textbox2" placeholder="password" id="loginPassword" required /><span class="form_hint">Enter your password</span>
										<p>
											<img src="images/lock.png" alt="">
										</p></li>
								</ul>
								<a type="submit" id="SignInButton" value="Sign In" onclick="login()">Sign In</a>

								<div class="clear"></div>
							</form>
							<!-- end-form -->
							<!-- start-account -->
							<div class="account">
								<h2>
									<a href="#" data-dismiss="modal" data-toggle="modal" data-target="#signup">Don't have an account? Sign Up!</a>
								</h2>
								<div class="span" onclick="loginWithFacebook()">
									<a href="#"><img src="images/facebook.png" alt="" /><i>Sign In with Facebook</i>
										<div class="clear"></div></a>
								</div>
								<div class="span1">
									<a href="#"><img src="images/twitter.png" alt="" /><i>Sign In with Twitter</i>
										<div class="clear"></div></a>
								</div>
								<div class="span2">
									<a href="#"><img src="images/gplus.png" alt="" /><i>Sign In with Google+</i>
										<div class="clear"></div></a>
								</div>
							</div>
							<!-- end-account -->
							<div class="clear"></div>
						</div>
					</div>
					<!-- end-contact-form -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg wrap" id="signup" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

					<div class="modal-body">
						<div id="card">
							<h10>Sign-up</h10>

							<ul>

								<li><input type="text" id="usernameSignup" onInput="checkInputs()" name="name" class="decoratedNormalForm" placeholder="Username..." /> <span class="form_hint">Enter a valid
										username</span></li>
								<li><input type="text" id="mailSignup" onInput="checkInputs()" class="decoratedNormalForm" name="mail" placeholder="Mail..." /></li>
								<li><input type="password" id="passwordSignup" onInput="checkInputs()" name="password" class="decoratedNormalForm" placeholder="Password..." /></li>
								<li><input type="submit" id="submitSignup" onclick="submitSignup()" name="submit" disabled /></li>
							</ul>
						</div>

					</div>

					<!-- end-form -->

					<div class="clear"></div>
				</div>
			</div>
			<!-- end-contact-form -->
		</div>
	</div>
	<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



</body>
</html>