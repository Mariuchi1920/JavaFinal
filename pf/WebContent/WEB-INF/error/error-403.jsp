<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>		
		<title>Error 403 - Forbidden</title>
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	</head>
	<body>
			
		<div class="container">
			<h2>La solicitud no está permitida.</h2>
   			<a href="${pageContext.request.contextPath}/home" class="btn btn-info btn-lg">
   				<span class="glyphicon glyphicon-home"></span> Ir al inicio 
   			</a>
    		<hr>
	    		<jsp:include page="/WEB-INF/pie.jsp" />
    	</div>
		
		
	</body>
</html>