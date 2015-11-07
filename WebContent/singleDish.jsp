<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page import="project.database.JsonDBManager"%>
<%@page import="project.beans.*"%>
<%@page import="project.database.BeanDBManager"%>
<%@page import="com.fasterxml.jackson.*"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" /> -->
<script src="javaScript/dishes.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/dishes.css" />
<link rel="stylesheet" type="text/css" href="css/star-rating.css" />
<script src="javaScript/star-rating.js" type="text/javascript"></script>


</head>
<body>

	<%
		String jSonDish = JsonDBManager.getInstance().getDishFromID(Integer.parseInt(request.getParameter("id")));

		JsonNode dishNode = new ObjectMapper().readTree(jSonDish);
	%>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="row">
				<div class="box10" id=<%=dishNode.get(0).get("id")%>>
					<h4><%=dishNode.get(0).get("name").asText()%></h4>
					<div class="col-sm-4">
						<%
							if (dishNode.get(0).get("image_url").asText().equals( "null") || dishNode.get(0).get("image_url").asText().equals(""))
							{
								
						%>
						<img id="preview" src="images/missing.png" alt=<%=dishNode.get(0).get("name").asText()%> class="img-circle" />
						<%
							} else
							{
						%>
						<img id="preview" src=<%=dishNode.get(0).get("image_url").asText()%> alt=<%=dishNode.get(0).get("name").asText()%> class="img-circle" />
						<%
							}
						%>
					</div>

					<%
						if (!dishNode.get(0).get("description").asText().equals("null"))
						{
					%>
					<div class="col-sm-5" id="description"><%=dishNode.get(0).get("description").asText()%></div>
					<%
						} else
						{
					%>
					<div class="col-sm-4" style="padding-bottom: 10%" id="description">Descrizione non disponibile</div>
					<%
						}
					%>

					<%
						User user = (User) session.getAttribute("user");
						System.out.println(user);
						if (user != null)
						{
							String userRating = JsonDBManager.getInstance().getDishRatingByUser(Integer.parseInt(request.getParameter("id")), user);

							JsonNode userRatingNode = new ObjectMapper().readTree(userRating);
					%>
					<div class="rating">
						<input id="input-21e" onchange='addRating(<%=dishNode.get(0).get("id")%>,"<%=((User) session.getAttribute("user")).getUsername()%>")'
							value=<%=userRatingNode.size() > 0 ? userRatingNode.get(0).get("points").asInt() : 0%> type="number" class="rating form-control hide" min="0" max="5" step="0.5" data-size="xs">


					</div>
					<%
						}
					%>
				</div>
			</div>

			<%
				if (user != null)
				{
			%>
			<div class="row">

				<div class="widget-area no-padding blank" id="divTextArea">
					<div class="status-upload">
						<form>
							<textarea id="comment" placeholder="What are you doing right now?"></textarea>

							<button type="button" onclick='addComment (<%=dishNode.get(0).get("id")%>,"<%=user.getUsername()%>")' class="btn red">Share</button>
						</form>
					</div>
					<!-- Status Upload  -->
				</div>
				<!-- Widget Area -->
			</div>
			<%
				}
			%>
			<div class="row">
				<span>inside the element</span>
			</div>
			<div class="row " id="comments">
				<%
					String jSonComments = JsonDBManager.getInstance().getDishComments(Integer.parseInt(request.getParameter("id")));
					JsonNode arrNode = new ObjectMapper().readTree(jSonComments);
					if (arrNode.isArray())
					{
						int commentID = 1;
						for (final JsonNode objNode : arrNode)
						{
							String username = objNode.get("username").asText();

							User currentUser = BeanDBManager.getInstance().getUser(username);
							String urlImage = currentUser.getImageUrl();
							String text = objNode.get("text").asText();
							String data = objNode.get("data").asText();
							String profileName = objNode.get("profile_name").asText();
							boolean social = objNode.get("social").asBoolean();
				%>

				<div class="row">

					<div id="comment<%=commentID%>">
						<div class="col-sm-2">
							<div class="thumbnail">

								<img class="img-responsive user-photo" src=<%=(urlImage == null) ? "https://ssl.gstatic.com/accounts/ui/avatar_2x.png" : urlImage%>>
							</div>
							<!-- /thumbnail -->
						</div>
						<!-- /col-sm-1 -->

						<div class="col-sm-10">
							<div class="panel panel-default">
								<div class="panel-heading">
									<%
										if (social)
												{
									%>
									<strong><%=profileName%></strong>
									<%
										} else
												{
									%>
									<strong><%=username%></strong>
									<%
										}
									%>
									<span class="text-muted">commented <%=data%></span>
									<%
										if (user != null && username.equals(user.getUsername()))
												{
									%>
									<span class="text-muted" id="postIcon" style='float: right'> <img
										onclick="modifyComment('comment<%=commentID%>','<%=data%>',<%=request.getParameter("id")%>,'<%=username%>','<%=text%>','<%=urlImage%>','<%=profileName%>', <%=social%>)"
										src="images/editIcon.png" height="20px" /> <img onclick="deleteComment('<%=data%>',<%=request.getParameter("id")%>,'<%=username%>','comment<%=commentID%>')" src="images/deleteIcon.png"
										height="20px" /></span>
									<%
										}
									%>
								</div>
								<div class="panel-body"><%=text%></div>
							</div>
						</div>


						<%
							commentID++;
						%>
					</div>
				</div>
				<%
					}
					}
				%>

			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>
</html>