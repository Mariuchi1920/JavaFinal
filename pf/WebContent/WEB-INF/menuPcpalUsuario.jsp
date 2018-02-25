<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta charset="UTF-8">
<title>Menu Principal</title>
<meta content="width=device-width", user-scalable=no, initial-scale=1.0," name="viewport">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div id="contenedor">

		<<jsp:include page="/WEB-INF/cabecera.jsp" />

	</div>
	
	<jsp:include page="/WEB-INF/menu.jsp" />
	
	<div id="Pie">
		<jsp:include page="/WEB-INF/pie.jsp" />

	</div>
	</div>
		<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>