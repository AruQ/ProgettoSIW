<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

var contextPath = "<%=request.getContextPath()%>";
</script>
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

<script src="javaScript/dishes.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/dishes.css" />
<link rel="stylesheet" type="text/css" href="css/star-rating.css" />
<script src="javaScript/star-rating.js" type="text/javascript"></script>





</head>
<body>

	<script type="text/javascript">
		$(document).ready(function() {
			init();

		})
	</script>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">

			<div class="row">
				<div id="dishes"></div>
			</div>
			<div class="col-md-2"></div>
		</div>

	</div>



</body>
</html>