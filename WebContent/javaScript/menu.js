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
			$("#mainIndex").load("menu.jsp?date=" + date + "&dateFormat=" + "MM/dd/yyyy");
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
