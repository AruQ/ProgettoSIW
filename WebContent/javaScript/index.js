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
	// location.reload(true);

}
