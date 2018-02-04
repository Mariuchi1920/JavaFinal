<%@page import="entidad.Institucion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		out.println(request.getAttribute("idinstitucion"));
		int id= Integer.parseInt(request.getParameter("idinstitucion"));
		Institucion i= new Institucion ();
		i.eliminarInstitucion(id);
		out.println("registro eliminado");
		request.getRequestDispatcher("maestroInstituciones.jsp").forward(request, response);
		
	%>
</body>
</html>