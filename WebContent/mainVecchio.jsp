<%@page import="project.beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	var contexPath ='<%=request.getContextPath()%>	';
</script>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="css/js-image-slider.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/signup.css" rel="stylesheet" type="text/css" />
<script src="javaScript/login.js" type="text/javascript"></script>
<script src="javaScript/signup.js" type="text/javascript"></script>
<script src="javaScript/index.js" type="text/javascript"></script>
<script src="javaScript/map.js" type="text/javascript"></script>



</head>
<body>

	<script type="text/javascript">
		$(document).ready(function() {
			/* loadPositions(); */
			loadHome();
		})
	</script>


	<div class="row">
		<div id="headerwrapper">
			<div class="col-lg-2"></div>
			<div id="header" class="col-lg-8">
				<div class="logo">
					<a href="#"><img src="images/logo_unical.png" alt="logo" width="220" height="74"></a>
				</div>
				<!--end of Logo-->
				<nav>
				<ul id="navigations">
					<li><a href="#" onclick="loadHome()">HOME</a></li>
					<li><a href="#slider">MENU</a></li>
					<li><a href="#map">LOCATONS</a></li>
					<li><a href="#" onclick="loadDishes()">DISHES</a></li> 

					<%
						User user = (User) session.getAttribute("user");
						if (user != null)
						{
					%>
					<li data-toggle="modal" data-target="#login"><a href="#">LOG IN</a></li>
					<%
						} else
						{
					%>
					<li>
							<a aria-expanded="true" data-toggle="dropdown" class="" data-target="#userDetails"><span><%="Benvenuto, " + user%><i class="caret"></i></span></a>
							</li>
					<!-- 	<ul class="nav navbar-nav navbar-right navbar-nav-fancy">
						</ul> -->

				</ul>
				</nav>
			</div>
		</div>
		<%
			}
		%>


		<!--end of header-->
		<div class="col-lg-2"></div>
	</div>


	<!-- end of headerwrapper-->


	<!-- <div id="background">
	</div> -->
	<div class="row" style="margin-top: 150px; background-color: white""></div>
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8" id="mainIndex">

			<div class="col-lg-2"></div>
		</div>
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
									<li><input type="password" name="website" class="textbox2" placeholder="password" id="loginPassword" required>
										<p>
											<img src="images/lock.png" alt="">
										</p></li>
								</ul>
								<input type="submit" name="Sign In" value="Sign In" onclick="login()" />
								<div class="clear"></div>
								<label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i>Remember me</label>
								<div class="forgot">
									<a href="#">forgot password?</a>
								</div>
								<div class="clear"></div>
							</form>
							<!-- end-form -->
							<!-- start-account -->
							<div class="account">
								<h2>
									<a href="#" data-dismiss="modal" data-toggle="modal" data-target="#signup">Don't have an account? Sign Up!</a>
								</h2>
								<div class="span">
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

							<input type="text" id="usernameSignup" onInput="checkInputs()" name="name" class="decoratedNormalForm" placeholder="Username..." /> <input type="text" id="mailSignup" onInput="checkInputs()"
								class="decoratedNormalForm" name="mail" placeholder="Mail..." /> <input type="password" id="passwordSignup" onInput="checkInputs()" name="password" class="decoratedNormalForm"
								placeholder="Password..." /> <input type="submit" id="submitSignup" onclick="submitSignup()" name="submit" disabled />

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