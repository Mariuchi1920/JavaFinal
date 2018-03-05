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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/font-awesome/css/fontawesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway:100,300,400,500" rel="stylesheet">
	<link rel="stylesheet"type="text/css" href="${pageContext.request.contextPath}/CSS/estilos.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"> 

<title>Agregar Equipos AL torneo</title>


</head>

<script type="text/javascript">
	
	function agregar(met) {
		if(confirm("Estas seguro que desea agregar al Equipo del Torneo? ")){
			document.myForm.eliminar.value="";
		    document.myForm.action=met;
		};
    }
	function eliminar(met) {
		if(confirm("Estas seguro que desea eliminar al Equipo del Torneo? ")){
			document.myForm.editar.value="";
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
	LinkedList<EquiposTorneos> listarEqTorneo = null; 
	
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

<div class="container-fluid">
	
		<div id="contenedor">
			<jsp:include page="/WEB-INF/cabecera.jsp" />
		</div>
		
		
		
	
	<div class="row">
			<div class="col-sm-12 mysocial-login">
				
				<%
				
				if(request.getSession().getAttribute("error")!=null){
				
				%>
				<h3 style="color: red;"><%=request.getSession().getAttribute("error").toString()%></h3>
				<%
				request.getSession(false).setAttribute("error" , null);
				
				} %>
			
				
				
			</div>
	</div>
	
	
	

<div class="container tablaPersona">
			
				 <h1>Administrador de equipos para un torneo</h1> 
				 <form id="myForm" name="myForm" action="" method="post">
				 <table class="table table-bordered" align="center">
				 <thead>
			
			<%
					   if(listarEqTorneo!=null && listarEqTorneo.size()>0){%>
				<tr>
				  <td > Equipos dentro del Torneo </td>
				  <td >Accion </td>
					
					
					
				</tr>
				</thead>
				<tbody>
				
				  
				  
					<%
					   for(EquiposTorneos equipo : listarEqTorneo){%>
						<tr>   
						  <td > <%= equipo.getEquipos().getInstitucion().getNombre() %>-<%= equipo.getEquipos().getCategorias().getAñoCategoria() %>-<%= equipo.getEquipos().getNombreEquipo() %></td>
						  
						 
						   <td colspan="1"><button class="botonEliminar" 
						    onclick="javascript: eliminar('${pageContext.request.contextPath}/admin/agregarEquiposTorneos/')"
				               id="eliminar" value=" <%= equipo.getEquipos().getInstitucion().getIdInstituciones() %>/<%= equipo.getEquipos().getCategorias().getIdCategorias() %>/<%= equipo.getEquipos().getNombreEquipo() %>" name="eliminar">Eliminar
						   </button> </td>
						</tr>
						   <%  }  %>


                       
					
					
					
					
					
					
				</tbody>
				   <%  }
					    %>
			
				
 						
					 <% 
					 EquiposDAO catEquipos = new EquiposDAO();
					 LinkedList<Equipo> listarEquipos = catEquipos.listarTodasLosEquipos();
					 if(listarEquipos!=null && listarEquipos.size()>0){ 
						 int cantidad =0;
						 if(listarEqTorneo != null ){
							 cantidad=listarEqTorneo.size() ;
						 }
					     if(cantidad < listarEquipos.size() ){ %>
					 
					 <tr>
				  
				  
					<td colspan="2"> Equipos </td>
					
					
					
				</tr>
				
				
				
				
				<tr>
					
                        <td>

				
					
					   <select name="listaEquipos" id="listaEquipos">
							<%
							  
							if(listarEqTorneo!=null){
								for(EquiposTorneos i :listarEqTorneo){
									listarEquipos = Equipo.removeEquipoLista(listarEquipos , i.getEquipos());
								}
							}
							  
							    for(Equipo te :listarEquipos){ %>
									  
							             <option value="<%= te.getInstitucion().getIdInstituciones() %>/<%= te.getCategorias().getIdCategorias() %>/<%= te.getNombreEquipo() %>"><%= te.getInstitucion().getNombre() %>-<%= te.getCategorias().getAñoCategoria() %>-<%= te.getNombreEquipo() %></option>
							    
									  <% } %>

					</select>
					
					 


					</td>
					
					
					<td>
				  <button  class="botonEditar" 
				 onclick="javascript: agregar('${pageContext.request.contextPath}/admin/agregarEquiposTorneos/')"
				   id="editar" value="editar" name="editar" style="border: navy;">Agregar</button>
				</td>
				
				<% }} %>
				
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