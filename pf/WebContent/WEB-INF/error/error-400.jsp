<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>		
		<title>Error 400 - Bad Request</title>
	
	</head>
	<body>
		
		<div id="contenedor">
		<jsp:include page="/WEB-INF/cabecera.jsp" />
	    </div>
		<div class="col-sm-12 mysocial-login">
			<h3 style="color: red;">Ocurrio un error inesperado, vuelva a intentarlo</h3>
   			<a href="${pageContext.request.contextPath}/admin" class="btn btn-info btn-lg">
   				<span class="glyphicon glyphicon-home"></span> Volver al menu</a>
    		
    		
    	<div>	
	    		<jsp:include page="/WEB-INF/pie.jsp" />
    	</div>
		
		
	</body>
</html>