<%@page import="datos.JornadaDAO"%>
<%@page import="entidad.Jornadas"%>
<%@page import="entidad.Torneo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="datos.TorneosDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>Listar Torneos</title>


</head>

<script type="text/javascript">
	
	function editar(met) {
		document.myform.verfixture.value="";
		document.myform.fixture.value="" ;
	    document.myform.eliminar.value="";  
	    document.myForm.action=met;
		
    }
	
	function eliminar(met) {
		if(confirm("Estas seguro de eliminar el Torneo?")){
			document.myform.verfixture.value="";
			document.myform.fixture.value="";
			document.myform.editar.value=""  ;
		    document.myForm.action=met;
		};
    }
	
	function fixture(met) {
		if(confirm("Estas seguro de realizar el Fixture?")){
			document.myform.verfixture.value="";
			document.myform.editar.value="" ;
		    document.myform.eliminar.value="";  
		    document.myForm.action=met;
		};
    }
	
	function verfixture(met) {
	     	document.myform.fixture.value="";
			document.myform.editar.value="" ;
		    document.myform.eliminar.value="";  
		    document.myForm.action=met;
	
    }
	
	
	
	</script>

<body>

<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		<div class="container tablaPersona">
			
				 <h1>Administrador de Torneo</h1>
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
  <thead>
 				<tr>


					<td align="center">Nombre</td>
					<td align="center">Fecha Inicio</td>
					<td align="center">Fecha Fin</td>
					<td align="center">Estado</td>
					<td align="center">Equipo Campeon</td>
					<td align="center" colspan="4">Accion</td>
				</tr>
  
  </thead>
  <%
					TorneosDAO torneoDao = new TorneosDAO();
					LinkedList<Torneo> torneos = torneoDao.listarTodosLosTorneos();
					for (Torneo t : torneos) {
				%>
  <tbody>
  <tr>

					<th><%=t.getNombre()%></th>
					<td><%=t.getFechaInicio()%></td>
					<td><%=t.getFechaFin()%></td>
					<td><%=t.getEstado().getDescripcion()%></td>
					<%
						if (t.getEquipoGanador() != null) {
					%>
					<td><%=t.getEquipoGanador().getInstitucion().getNombre()%>-
						<%=t.getEquipoGanador().getCategorias().getAñoCategoria()%> - <%=t.getEquipoGanador().getNombreEquipo() %> </td>
					<%
						} else {
					%>
					<td>-</td>
					<%
						}
					%>
					<th><button class="botonEditar"
							onclick="javascript: editar('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="editar" name="editar">Editar</button>
					</th>
					<th><button class="botonEliminar"
							onclick="javascript: eliminar('/admin/listarTorne/')"
							value="<%=t.getIdTorneos()%>" id="eliminar" name="eliminar">Eliminar</button>
					</th>

					<th><button class="botonFixture"
							onclick="javascript: fixture('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="fixture" name="fixture">Fixture</button>

					
					<%
						JornadaDAO catJornada = new JornadaDAO();
					    LinkedList<Jornadas> listaJornada = catJornada.buscarporTorneos(t.getIdTorneos());
					    if(listaJornada!=null && listaJornada.size()>0){
					%>
					 <th><button class="verFixture"
							onclick="javascript: verfixture('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="verfixture" name="verfixture">Ver Fixture</button>

					</th>
					
					<%
					}else{
				%>
				    <th><button class="botonGenerarFixture"
							onclick="javascript: fixture('/admin/listarTorneo/')"
							value="<%=t.getIdTorneos()%>" id="fixture" name="fixture">Generar Fixture</button>
					</th>
				<%
					}
				%>
					
				</tr>
				<%
					}
				%>
				<th colspan="10"> <a type="button" href="${pageContext.request.contextPath}/admin/modificarTorneo">Nuevo Torneo</a></th>
				


			</table>
		</form>

	</div>

		<div class= "container Pie">
			<div id="Pie">
				<jsp:include page="/WEB-INF/pie.jsp" />


			</div>
		</div>

	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>