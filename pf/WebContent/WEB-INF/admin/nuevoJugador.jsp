<head>
	<meta charset="UTF-8">
	<title>Liga Efa!</title>
	
</head>
<body>
<div id="contenedor">

		<jsp:include page="cabecera.jsp" />

	</div>

			<form method="Post" action="regJugador">
			<fieldset>
  				<legend>Alta de jugador</legend>
 
  				<label for="nombre">Nombre:</label>
  				<input type="text" id="nombre" /><br>
 
  				<label for="apellidos">Apellidos:</label>
  				<input type="text" id="apellido" size="50" /><br>
  				
  				<label for="fechaNacimiento">Fecha de nacimiento:</label>
  				<input type="text" id="fechaNacimiento" size="50" /><br>
 				
 				<label>Tipo documento:</label>
 				<select name="tipoDni" id="tipoDocumento">
 				<option value="DNI" id="dni">dni</option>
 				<option value="LC" id="lc">Lc</option>
 				<option value="pasaporte" id="pasaporte">pasaporte</option>
 				</select><br>
 				
  				<label for="dni">Nro de documento:</label>
  				<input type="text" id="dni" size="10" maxlength="9" /><br>
 
 				<label for="celular">Celular:</label>
  				<input type="celular" id="celular" /><br>
  				
 				<label for="email">Email:</label>
  				<input type="email" id="email" /><br>
 
  				
			</fieldset>
			<input class="btn" type="submit" value="Registrar" />
			</form>

	<div id="Pie">
		<jsp:include page="pie.jsp" />

	</div>

		
</body>
</html>