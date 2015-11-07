<%@page import="project.beans.*"%>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />



<%
	User user = (User) session.getAttribute("user");
	if (user == null)
	{
		response.sendError(404);
	} else
	{
%>


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
									<a href='<%=request.getContextPath()%>/admin'><img src="http://www.berk-tel.com/images/button_admin.gif" alt="admin" /></a>
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