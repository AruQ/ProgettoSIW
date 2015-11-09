<%@page import="project.database.*, java.text.*,java.util.*,project.beans.*,com.fasterxml.jackson.databind.*"%>
<%
		String selectedDate = request.getParameter("date");
		String dateFormatString = request.getParameter("dateFormat");

		DateFormat fromFormat = new SimpleDateFormat(dateFormatString);
		DateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = fromFormat.parse(selectedDate);
		String currentDate = toFormat.format(date);
	%>
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
							<p><%=(objNode.get("description").asText().equals("null") || objNode.get("description").asText().equals("")) ? "Descrizione non disponibile" : objNode.get(
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

