<%@page import="com.fasterxml.jackson.databind.JsonNode,project.database.*,project.beans.*,com.fasterxml.jackson.*,com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(function() {
		//	loadPositions();
		});
	</script>

  
  <div class="row" id="sliderWrapper">
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

      <div class="item active">
        <img src="images/MENSA1.jpg" alt="mensa" width="400px" height="200px">
      </div>

      <div class="item">
        <img src="images/MENSA2.jpg" alt="mensa" width="400px" height="200px">
      </div>
    
      <div class="item">
        <img src="images/MENSA3.jpg" alt="mensa" width="400px" height="200px">
      </div>

      <div class="item">
        <img src="images/MENSA4.jpg" alt="mensa" width="400px" height="200px">
      </div>
  
    </div>

    <!-- Left and right controls -->
   
  </div>
</div>

	<div class="row" id="bestDishesWrapper">
		<h2>BEST DISHES</h2>
		<%
			String jSonComments = JsonDBManager.getInstance().bestDishes(4);
			JsonNode arrNode = new ObjectMapper().readTree(jSonComments);
			if (arrNode.isArray())
			{
				for (final JsonNode objNode : arrNode)
				{
					String dishName = objNode.get("name").asText();
					String urlImage = objNode.get("image_url").asText();
					int dishID = objNode.get("id").asInt();
		%>
		<div class="col-md-3" onclick="showDish(<%=dishID%>)">
			<div class="row">
				<img id="immagine" src="<%=urlImage%>" />
			</div>
			<div class="row">
				<h3><%=dishName%></h3>
			</div>
		</div>


		<%
			}
			}
		%>
	</div>
	<div class="row" id="mapWrapper">
		<div class="row">
			<h2>LOCATION</h2>
		</div>
		<div class="row" id="map"></div>
	</div>
