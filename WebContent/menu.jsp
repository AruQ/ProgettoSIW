<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="project.database.*, java.text.*,java.util.*,project.beans.*,com.fasterxml.jackson.databind.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="javaScript/menu.js"></script>

<link href="css/dailyMenu.css" rel="stylesheet" type="text/css" />
<link href="css/dishes.css" rel="stylesheet" type="text/css" />



<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

</head>
<body>

	<%
		String selectedDate = request.getParameter("date");
		String dateFormatString = request.getParameter("dateFormat");

		DateFormat fromFormat = new SimpleDateFormat(dateFormatString);
		DateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = fromFormat.parse(selectedDate);
		String currentDate = toFormat.format(date);
	%>

	<div class="row" id="dailyMenu">
		<div class="row">
			<img src="images/menuLogo.png" />
			<div class="container">

				<input type="image" id="datepicker" style="width: 40px; height: 40px;" src="http://iconshow.me/media/images/Mixed/small-n-flat-icon/png/512/calendar.png">

			</div>
		</div>

		<div class="row" id="dailyDishes">
			<h1 id="data"><%=currentDate%></h1>

			<%
				String toPrint = JsonDBManager.getInstance().getDishesByDay(currentDate);

				JsonNode arrNode = new ObjectMapper().readTree(toPrint);
				if (arrNode.isArray())
				{
					int dishesCount = 0;
					for (final JsonNode objNode : arrNode)
					{
						if (dishesCount % 2 == 0)
						{
			%>

			<div class="row">
				<%
					}
				%>
				<div class="col-md-6" id='singleDish' onclick="showDish(<%=objNode.get("id").asText()%>)">
					<div class="col-md-5">
						<img id="preview" src=<%=objNode.get("image_url").asText()%> alt=<%=objNode.get("name").asText()%> class="img-circle" />
					</div>

					<div class="col-md-7">
						<div class="row">
							<h2><%=objNode.get("name").asText()%></h2>
						</div>
						<div class="row">
							<p><%=(objNode.get("description").asText().equals("null")) ? "Descrizione non disponibile" : objNode.get(
							"description").asText()%></p>
						</div>
					</div>
				</div>
				<%
					if (dishesCount % 2 != 0)
							{
				%>

			</div>
			<%
				}
			%>

			<%
				dishesCount++;
					}
				}
			%>


		</div>
	</div>
</body>
</html>