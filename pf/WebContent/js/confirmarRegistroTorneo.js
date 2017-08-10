/**
 * 
 */
function carga(){
	 document.getElementById("nombreTorneo").focus();
	 
	 
}
function validarDatos() {
	 var nt= document.getElementById("nombreTorneo");
	var fi= document.getElementById("fechaIni");
	var e=document.getElementById("estado");
	
	
	if((nt.value==""||nt.value==''||nt.value== null)&&(fi.value==""||fi.value==''||fi.value== null)&&(e.value==""||e.value==''||e.value== null)){
		alert("Debes ingresar todos los datos para crear el torneo");
		u.focus();
		return false;
		
	}else if(nt.value==""||nt.value==''||nt.value== null){
		alert("Ingresa el nombre del torneo");
		u.focus();
		return false;
	}else if(fi.value==""||fi.value==''||fi.value== null){
		alert("ingresa la fecha de inicio");
		c.focus();
		return false
	}else if(e.value==""||e.value==''||e.value== null){
		alert("Ingresa el estado");
		c.focus();
		return false
	}else{
var mensaje = confirm("Esta seguro que desea registrar un Torneo nuevo?");
		
		//Detectamos si el usuario acepto el mensaje
		if (mensaje) {
		alert("Se ha registrado un Torneo nuevo");
		}
		//Detectamos si el usuario denegó el mensaje
		else {
			
		alert("¡Haz denegado el mensaje!");
		}
		return true;
	}
	
	
}