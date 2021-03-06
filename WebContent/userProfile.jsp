<%@page import="project.beans.*"%>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />


<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendError(404);
	} else {
%>

<script>
$(document).ready (function() {
	if (mobile)
		{
			$(".list-group-item").css ("margin","0 -35px");
			$("figure>img").css ("margin-bottom", "15px");
		}
});
function goToControlPanel(username) {
		$.ajax({
			type : "POST",
			url : contextPath + '/admin',
			data : {
				'username': username
			},
			success : function(data) {

				location.href=contextPath+"/admin";
			}

		});

	}
	
	
</script>


<div id="userDetail" class="container">


	<div class="resume">
		<header class="page-header">
			<h1 class="page-title">
				<%
					System.out.println(user.toString());
						if (user.getProfileName() != null && !user.getProfileName().equals(""))
						{
				%>
				Resume of
				<%=user.getProfileName()%>
				<%
					} else
						{
				%>Resume of
				<%=user.getUsername()%>
				<%
					}
				%>
			</h1>
		</header>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
				<div class="panel panel-default">
					<div class="panel-heading resume-heading">
						<div class="row">
							<div class="col-lg-12">
								<div class="col-xs-12 col-sm-4">
									<figure>
										<%
											if (user.getImageUrl() == null || user.getImageUrl().equals(""))
												{
										%>
										<img class="img-circle img-responsive" alt="" src="images/profileUnknown.png">
										<%
											} else
												{
										%>
										<img class="img-circle img-responsive" alt="" src="<%=user.getImageUrl()%>">
										<%
											}
										%>
									</figure>



								</div>

								<div class="col-xs-12 col-sm-8">
									<ul class="list-group">
										<%-- 	<%
											if (user.getSocial())
												{
										%> --%>
										<li class="list-group-item">
											<h4 class="list-group-item-heading">Username</h4>
											<p class="list-group-item-text"><%=user.getUsername() == null ? "undefined" : user.getUsername()%></p>
										</li>
										<%-- 	<%
											}
										%> --%>
										<li class="list-group-item">
											<h4 class="list-group-item-heading">Profile name</h4>
											<p class="list-group-item-text"><%=user.getProfileName() == null ? "undefined" : user.getProfileName()%></p>
										</li>
										<li class="list-group-item">
											<h4 class="list-group-item-heading">Email</h4>
											<p class="list-group-item-text">
												<i class="fa fa-envelope"></i>
												<%=user.getEmail()%></p>
										</li>
									</ul>
									<%
										if (user.getAdmin())
											{
									%>
									<div class="divider"></div>
									<a id="controlPanelButton" class="btn btn-success btn-sm btn-block" onclick="goToControlPanel(<%=user.getUsername()%>)"><i class="fa fa-user"></i>&nbsp;Administration</a>
									<%
										}
									%>
								</div>
							</div>
						</div>
					</div>





				</div>

			</div>
		</div>

	</div>

</div>
<%
	}
%>