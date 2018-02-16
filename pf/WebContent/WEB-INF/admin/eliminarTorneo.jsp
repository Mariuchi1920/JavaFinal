<%@page import="entidad.Torneo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Torneo</title>
</head>
<body>
<%

if(Integer.parseInt(request.getParameter("idtorneo"))!=0){
	int idtorneo=Integer.parseInt(request.getParameter("idtorneo"));
	Torneo t= new Torneo();
	boolean respuesta= t.eliminarTorneo(idtorneo);
	if(respuesta!= false){
		// escribo mensaje de que se ha borrado un torneo exitosamente
		%>
		<script src="mensajeEliminado.js"></script>
		<%
		//regreso a la misma pagina
		request.getRequestDispatcher("maestroTorneo.jsp").forward(request, response);
		
	}else{
		// mensaje de error
		request.getRequestDispatcher("maestroTorneo.jsp").forward(request, response);
	}
	
	
}
	%>

</body>
</html>