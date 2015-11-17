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



	<div class="row" id="dailyMenu">
		<div class="row">
			<img src="images/menuLogo.png" />
			

				<div class="col-xs-7 col-md-10" style="margin: 0">
				<div class="container">
					<input type="image" id="datepicker" style="width: 40px; height: 40px;" src="http://iconshow.me/media/images/Mixed/small-n-flat-icon/png/512/calendar.png">
				</div>
			</div>
				<div class="col-xs-5 col-md-2" >
					<a href="#" id="myButton"  style="text-align: center" onclick="getTotalPoints()">TOTAL POINTS</a>
				</div>
		</div>

		<div class="row" id="dailyDishes">

			<jsp:include page="dailyMenu.jsp" />

		</div>
	</div>
</body>
</html>