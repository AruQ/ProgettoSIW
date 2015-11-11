function loadDishes() {
	$("#mainIndex").load("dishes.jsp");

}

function loadMenu(currentData, dateFormat) {
	$("#mainIndex").load("menu.jsp?date=" + currentData + "&dateFormat=" + dateFormat);

}

function showDish(dishID) {

	$("#mainIndex").load("singleDish.jsp?id=" + dishID);

}

function goToMap() {

	$.ajax({
		type : "POST",
		url : contextPath + '/TotalPointsServlet',
		/*
		 * data : { 'username' : username, 'dishID' : dishID, 'time' : time
		 *  },
		 */
		success : function(data) {
			alert("CIAO");

			// $("#mainIndex").load("singleDish.jsp?id=" + dishID);

		}

	});
	// location.href = "#mapWrapper";

}

function setMessage(type) {
	hideAllMessages();
	$('.' + type).animate({
		top : "0"
	}, 500);
	setTimeout(function() {
		$('.' + type).animate({
			top : -$(this).outerHeight()
		}, 600);
	}, 2000);

}
