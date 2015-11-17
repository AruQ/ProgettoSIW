var cafeteriaPosition = [];
function loadPositions() {

	$.ajax({
		type : "POST",
		url : contextPath + '/CafeteriaPositionServlet',
		success : function(data) {
			var responseJson = eval('(' + data + ')');
			for (var int = 0; int < responseJson.length; int++) {
				var obj = {
					name : responseJson[int]["name"],
					position : new google.maps.LatLng(responseJson[int]["latitude"], responseJson[int]["longitude"]),
					link : '#slider'
				};

				cafeteriaPosition[int] = obj;
			}

			// alert($("#map").length);
			google.maps.event.addDomListener(window, 'load', initialize);

		},
	});
}

var minZoomLevel = 15;
function initialize() {
	console.log($("#map").length);
	var mapCanvas = document.getElementById('map');
	var mapOptions = {
		center : new google.maps.LatLng(39.358078, 16.225709),
		zoom : minZoomLevel,
		mapTypeId : google.maps.MapTypeId.HYBRID
	}
	var map = new google.maps.Map(mapCanvas, mapOptions);
	var bounds = new google.maps.LatLngBounds();
	var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';

	var infoWindow = new google.maps.InfoWindow(), marker, int;
	for (var int = 0; int < cafeteriaPosition.length; int++) {
		var cafeteria = cafeteriaPosition[int];
		bounds.extend(cafeteria["position"]);
		marker = new google.maps.Marker({
			position : cafeteria["position"],
			map : map,
			title : cafeteria["name"],
			icon : iconBase + 'dining.png'
		});

		google.maps.event.addListener(marker, 'click', (function(marker, int) {
			return function() {

				var contentString = '<div id="content">' + '<div id="siteNotice">' + '</div>' + '<h1 id="firstHeading" class="firstHeading"> <a href=' + cafeteriaPosition[int]["link"] + ' >' + cafeteriaPosition[int]["name"] + '</a></h1>' + '<div id="bodyContent">' + '</div>' + '</div>';
				infoWindow.setContent(contentString);
				infoWindow.open(map, marker);
			}
		})(marker, int));

		map.fitBounds(bounds);
	}

	// Bounds for North America
	var strictBounds = new google.maps.LatLngBounds(new google.maps.LatLng(39.40431, 16.120664), new google.maps.LatLng(39.352879, 16.333407));

	// Listen for the dragend event
	google.maps.event.addListener(map, 'dragend', function() {
		if (strictBounds.contains(map.getCenter()))
			return;

		// We're out of bounds - Move the map back within the bounds

		var c = map.getCenter(), x = c.lng(), y = c.lat(), maxX = strictBounds.getNorthEast().lng(), maxY = strictBounds.getNorthEast().lat(), minX = strictBounds.getSouthWest().lng(), minY = strictBounds.getSouthWest().lat();

		if (x < minX)
			x = minX;
		if (x > maxX)
			x = maxX;
		if (y < minY)
			y = minY;
		if (y > maxY)
			y = maxY;

		// map.setCenter(new google.maps.LatLng(y, x));
	});

	// Limit the zoom level
	google.maps.event.addListener(map, 'zoom_changed', function() {
		if (map.getZoom() < minZoomLevel)
			map.setZoom(minZoomLevel);
	});
}
