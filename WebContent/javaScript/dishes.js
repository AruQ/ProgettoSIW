function init() {

	$.ajax({
		type : "POST",
		url : contextPath + '/DishServlet',
		success : function(data) {
			var responseJson = eval("(" + data + ")");
			if (responseJson != null) {
				var toAppend = "";
				for (var i = 0; i < responseJson.length; i++) {
					var categoryName = responseJson[i]["category"];
					toAppend += '<div class="box1" id ="' + categoryName + '"><h2>' + categoryName + '</h2>';
					var keys = Object.keys(responseJson[i]);
					var dishes = responseJson[i]["dishes"];
					for (var j = 0; j < dishes.length; j++) {
						toAppend += '<div class="box10" id ="' + dishes[j]["id"] + '"><h4 onclick="showDish(' + dishes[j]["id"] + ')">' + dishes[j]["name"] + '</h4>';
						toAppend += '<div class="col-lg-4"><img id="preview" src=" ';
						if (dishes[j]["image_url"] == null || dishes[j]["image_url"] == "") {
							toAppend += "images/missing.png";
						} else {
							toAppend += dishes[j]["image_url"];
						}
						toAppend += '"alt="' + dishes[j]["name"] + '" class="img-circle"/></div>';
						var percentuage = (57 * dishes[j]["avg_rating"]) / 5;
						if (dishes[j]["description"] == null) {
							toAppend += '<div class="col-lg-6" id="description">Descrizione non disponibile</div>';
						} else {
							toAppend += '<div class="col-lg-6" style="padding-bottom: 10%" id="description">' + dishes[j]["description"] + '</div>';
						}
						toAppend += '<div id="rating" class="rating-container rating-gly-star col-lg-2" data-content="" ><div class="rating-stars"  data-content="" style="width:' + percentuage + '%";"></div></div>';

						toAppend += '</div>';
					}

					toAppend += '</div>';
				}
			}

			$("#dishes").append(toAppend);
		},
	});
}

function showDish(dishID) {

	$("#mainIndex").load("singleDish.jsp?id=" + dishID);

}
var commentAddedID = -1;
function addComment(dishID, username) {

	console.log($("#comment").val());
	$.ajax({
		type : "POST",
		url : contextPath + '/AddComment',
		data : {
			'username' : username,
			'text' : $("#comment").val(),
			'dish' : dishID

		},
		success : function(data) {
			var responseJson = eval("(" + data + ")");
			if (responseJson != null) {

				if (responseJson.length != 0) {

					var toPrepend = '<div class="row"><div id= comment' + commentAddedID + '> <div class="col-sm-2"><div class="thumbnail"><img class="img-responsive user-photo" src=';
					if (responseJson[0]["image_url"] == null)
						toPrepend += "images/profileUnknown.png>";
					else
						toPrepend += "'" + responseJson[0]["image_url"] + "'>";
					toPrepend += '</div></div><div class="col-sm-10"><div class="panel panel-default"> <div class="panel-heading">';

					if (responseJson[0]["social"] == true) {
						toPrepend += "<strong>" + responseJson[0]["profile_name"] + "</strong>";
					} else {

						toPrepend += "<strong>" + responseJson[0]["username"] + "</strong>";
					}
					toPrepend += '<span class="text-muted"> commented ' + responseJson[0]["data"] + '</span><span class="text-muted"  id="postIcon" style="float: right">';
					toPrepend += ' <img onclick="modifyComment(\'comment' + commentAddedID + '\',\'' + responseJson[0]["data"] + '\',' + responseJson[0]["dish"] + ',\'' + responseJson[0]["username"] + '\',\'' + responseJson[0]["text"] + '\',\'' + responseJson[0]["image_url"] + '\',\''
							+ responseJson[0]["profile_name"] + '\',' + responseJson[0]["social"] + ')"' + ' src="images/editIcon.png" height="20px" />';
					toPrepend += '<img onclick="deleteComment(\'' + responseJson[0]["data"] + '\',' + responseJson[0]["dish"] + ',\'' + responseJson[0]["username"] + '\',\'comment' + commentAddedID + '\')"' + ' src="images/deleteIcon.png" height="20px" /></span>';
					toPrepend += ' </div> <div class="panel-body">' + responseJson[0]["text"] + '</div></div></div></div></div>';
					$("#comments").prepend(toPrepend);

					$("#divTextArea #comment").replaceWith('<textarea id="comment" placeholder="What are you doing right now?"></textarea>');
					// $("#mainIndex").load("singleDish.jsp?id=" +
					// dishID);
					commentAddedID--;
				} else {

				}
			}
		}

	});

}

function addRating(dishID, username) {

	$.ajax({
		type : "POST",
		url : contextPath + '/AddRating',
		data : {
			'username' : username,
			'dishID' : dishID,
			'pointsRating' : $('#input-21e').val()

		},
		success : function(data) {

		}

	});

}

function deleteComment(time, dishID, username, commentID) {

	$.ajax({
		type : "POST",
		url : contextPath + '/DeleteComment',
		data : {
			'username' : username,
			'dishID' : dishID,
			'time' : time

		},
		success : function(data) {

			$("#" + commentID).parent("div").empty();
			// $("#mainIndex").load("singleDish.jsp?id=" + dishID);

		}

	});

}

function modifyComment(commentID, time, dishID, username, text, urlImage, profile_name, social) {

	var toAppend = '<div id=' + commentID + '><div class="widget-area no-padding blank">	<div class="status-upload"><form><textarea id="comment" >' + text + '</textarea><button type="button" onclick= "updateComment(\'' + commentID + '\',' + dishID + ',\'' + username + '\',\'' + time + '\',\''
			+ urlImage + '\',\'' + profile_name + '\',' + social + ')"' + ' class="btn red">Share</button></form></div><!-- Status Upload  --></div> <div class="row"><span>inside the element</span></div>';
	$("#" + commentID).replaceWith(toAppend);

}

function updateComment(commentID, dishID, username, time, urlImage, profile_name, social) {

	var text = $("#" + commentID + " #comment").val();
	$.ajax({
		type : "POST",
		url : contextPath + '/UpdateComment',
		data : {
			'username' : username,
			'dishID' : dishID,
			'text' : text,
			'time' : time

		},
		success : function(data) {
			var responseJson = eval("(" + data + ")");
			if (responseJson != null) {

				if (responseJson["updated"] === 'true') {

					var toAppend = '<div id=' + commentID + '> <div class="col-sm-2"><div class="thumbnail"><img class="img-responsive user-photo" src=';
					if (urlImage == null)
						toAppend += "images/profileUnknown.png>";
					else
						toAppend += "'" + urlImage + "'>";
					toAppend += '</div></div><div class="col-sm-10"><div class="panel panel-default"> <div class="panel-heading">';

					if (social == true)
						toAppend += "<strong>" + profile_name + "</strong>";
					else
						toAppend += "<strong>" + username + "</strong>";

					toAppend += '<span class="text-muted"> commented ' + time + '</span><span class="text-muted"  id="postIcon" style="float: right">';
					toAppend += ' <img onclick="modifyComment(\'' + commentID + '\',\'' + time + '\',' + dishID + ',\'' + username + '\',\'' + text + '\',\'' + urlImage + '\')"' + ' src="images/editIcon.png" height="20px" />';
					toAppend += '<img onclick="deleteComment(\'' + time + '\',' + dishID + ',\'' + username + '\',\'' + commentID + '\')"' + ' src="images/deleteIcon.png" height="20px" /></span>';
					toAppend += ' </div> <div class="panel-body">' + text + '</div></div></div></div>';
					$("#" + commentID).replaceWith(toAppend);
				}
			}
		}

	});

}

// <input id="input-21e" value="0" type="number" class="rating form-control
// hide" min="0" max="5" step="0.5" data-size="xs">
