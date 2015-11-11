var dishCategory = {
	"1" : "0",
	"2" : "0",
	"3" : "0"
};

function clearArray() {
	dishCategory["1"] = 0;
	dishCategory["2"] = 0;
	dishCategory["3"] = 0;
}

function setDishSelected(idInput, category) {
	console.log(idInput);
	if ($("#checkboxDish" + idInput).is(":checked")) {

		dishCategory[category]++;
	} else {
		dishCategory[category]--;

	}

}

function getTotalPoints() {

	$.ajax({
		type : "POST",
		url : contextPath + '/TotalPointsServlet',
		data : {
			'primi' : dishCategory["1"],
			'secondi' : dishCategory["2"],
			'contorni' : dishCategory["3"]

		},
		success : function(data) {
			var responseJson = eval("(" + data + ")");
			if (responseJson != null) {
				$("p#totalPointsText").text("Totale punti " + responseJson["totalPoints"]);
				setMessage("totalPoints");
			}
		}
	});

}
var avaibleDays;

function checkData(fullDate) {
	var twoDigitMonth = fullDate.getMonth() + 1 + "";
	if (twoDigitMonth.length == 1)
		twoDigitMonth = "0" + twoDigitMonth;
	var twoDigitDate = fullDate.getDate() + "";
	if (twoDigitDate.length == 1)
		twoDigitDate = "0" + twoDigitDate;
	var currentDate = twoDigitDate + "-" + twoDigitMonth + "-" + fullDate.getFullYear();

	for (var i = 0; i < avaibleDays.length; i++) {
		if (avaibleDays[i]["menudate"] == currentDate) {
			return true;
		}
	}
	return false;
}
$(document).ready(function() {

	$.ajax({
		type : "POST",
		url : contextPath + '/MenuServlet',
		data : {
			'request' : 'days',

		},
		success : function(data) {
			var responseJson = eval("(" + data + ")");
			if (responseJson != null) {
				avaibleDays = responseJson;

			}
		}
	});

});
$(function() {
	$("#datepicker").datepicker({

		firstDay : 1,
		onSelect : function(date) {
			$("#dailyDishes").load("dailyMenu.jsp?date=" + date + "&dateFormat=" + "MM/dd/yyyy");
			clearArray();
		},
		beforeShowDay : function(fullDate) {

			if (checkData(fullDate)) {

				return [ 1 ];
			} else {

				return [ 0 ];
			}

		}
	});
});
