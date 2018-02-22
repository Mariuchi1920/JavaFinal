<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<<html class="no-js" lang="es">
    <head>
        <title>Error 500 - Not Found</title>
        <jsp:include page="/WEB-INF/EncabezadoUsu.jsp" />
    </head>
    <body>
    	
    	<div class="container">
    	<h1>Error 500</h1>
    		<h2>El servidor encontró un error interno que hizo que no pudiera rellenar este requerimiento..</h2>
   			<a href="${pageContext.request.contextPath}/login" class="btn btn-info btn-lg">
   				<span class="glyphicon glyphicon-home"></span> volver al inicio 
   			</a>
    		<hr>
	    <jsp:include page="/WEB-INF/pie.jsp" />
    	</div>
    	
    	
	</body>
</html>