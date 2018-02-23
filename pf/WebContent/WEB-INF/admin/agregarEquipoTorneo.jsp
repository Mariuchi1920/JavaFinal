<%@page import="entidad.EquiposTorneos"%>
<%@page import="datos.EquiposTorneoDAO"%>
<%@page import="entidad.TipoEstado"%>
<%@page import="java.sql.Date"%>
<%@page import="datos.TorneosDAO"%>
<%@page import="entidad.Equipo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="datos.EquiposDAO"%>
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

<script type="text/javascript">
	
	function agregar(met) {
		if(confirm("Estas seguro que desea agregar al Equipo al Torneo? ")){
			
		    document.myForm.action=met;
		};
    }
	
	
	</script>

<body>

	<%
	Torneo encontrado= null;
	int idTorneo=0;
	String nombre="";
	Date fechaI=null;
	Date fechaF=null;
	TipoEstado estado = new TipoEstado();
	Equipo equipoCampeon=null;
	EquiposTorneoDAO catEqTor = new EquiposTorneoDAO();
	LinkedList<EquiposTorneos> listarEqTorneo = new LinkedList<EquiposTorneos>(); 
	
	TorneosDAO torDao= new TorneosDAO();

	if(request.getSession().getAttribute("editador")!=null){
	   encontrado = (Torneo)request.getSession().getAttribute("editador");	  
	   idTorneo=encontrado.getIdTorneos();
	   nombre=encontrado.getNombre();
	   fechaI=encontrado.getFechaInicio();
	   fechaF=encontrado.getFechaFin();
	   estado=encontrado.getEstado();
	   equipoCampeon=encontrado.getEquipoGanador();
	   listarEqTorneo = catEqTor.buscarporTorneo(encontrado);
	   
	   
}
%>

<div id="contenedor">

		<jsp:include page="cabecera.jsp" />

	</div>
	
	
	
	<div id="contenido">


		<form class="form-signin" id="myForm" name="myForm" action=""
			method="post">
			<table border="4" align="center">
				<tr>
				  
				  
					<td colspan="2"> Equipos </td>
					
					
					
				</tr>
				
				<tr>
					
                        <td>
 				

					</select>
					
					   <select name="listaEquipos" id="listaEquipos">
							<%
							  EquiposDAO catEquipos = new EquiposDAO();
							  LinkedList<Equipo> listarEquipos = catEquipos.listarTodasLosEquipos();
							  for(EquiposTorneos i :listarEqTorneo)
							  listarEquipos.remove(i.getEquipos());
							  if(listarEquipos!=null && listarEquipos.size()>0){ 
							    for(Equipo te :listarEquipos){ %>
								  
								  <option value="<%= te.getInstitucion().getIdInstituciones() %>/<%= te.getCategorias().getIdCategorias() %>/<%= te.getNombreEquipo() %>"><%= te.getInstitucion().getNombre() %>-<%= te.getCategorias().getAñoCategoria() %>-<%= te.getNombreEquipo() %></option>
								  
								  <% }} %>

					</select>
					
					


					</td>
				</tr>
				<tr>
				<td>
				  <button 
				 onclick="javascript: agregar('${pageContext.request.contextPath}/admin/agregarEquiposTorneos')"
				   id="editar" value="editar" name="editar">Agregar</button>
				</td>
				</tr>
				
			</table>

			
		</form>

	</div>
	
	
	
	
		<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>


</body>
</html>