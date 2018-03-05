package entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;


import modelo.Conexion;

public class Institucion {
	
	int idInstituciones;
	String nombre;
	String nombreLocalia;
	String direccionLocalia;
	String nombreDelegado;
	String apellidoDelegado;
	String telefonoDelegado;
	String mailDelegado;

	



	public int getIdInstituciones() {
		return idInstituciones;
	}

	public void setIdInstituciones(int idInstituciones) {
		this.idInstituciones = idInstituciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreLocalia() {
		return nombreLocalia;
	}

	public void setNombreLocalia(String nombreLocalia) {
		this.nombreLocalia = nombreLocalia;
	}

	public String getDireccionLocalia() {
		return direccionLocalia;
	}

	public void setDireccionLocalia(String direccionLocalia) {
		this.direccionLocalia = direccionLocalia;
	}


	public String getNombreDelegado() {
		return nombreDelegado;
	}


	public void setNombreDelegado(String nombreDelegado) {
		this.nombreDelegado = nombreDelegado;
	}


	public String getApellidoDelegado() {
		return apellidoDelegado;
	}

	public void setApellidoDelegado(String apellidoDelegado) {
		this.apellidoDelegado = apellidoDelegado;
	}


	public String getTelefonoDelegado() {
		return telefonoDelegado;
	}


	public void setTelefonoDelegado(String telefonoDelegado) {
		this.telefonoDelegado = telefonoDelegado;
	}

	public String getMailDelegado() {
		return mailDelegado;
	}

	public void setMailDelegado(String mailDelegado) {
		this.mailDelegado = mailDelegado;
	}

	public static boolean validarInstitucion(Institucion i) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta= true;
		String nombre = i.getNombre();
		String nombreLocalia= i.getNombreLocalia();
		String direccionLocalia = i.getDireccionLocalia();
		String nombreDelegado = i.getNombreDelegado();
		String apellidoDelegado = i.getApellidoDelegado();
		String telefonoDelegado = i.getTelefonoDelegado();
		String mailDelegado = i.getMailDelegado();
		
		if(nombre.equals(null) || nombre.equals("null")){
			respuesta=false;
			throw new ApplicationException("Nombre institucion vacio");
			
		}else if(!Util.validateNombreApellido(nombre)){
			respuesta=false;
			throw new ApplicationException("Nombre no valido");
		}

		
		if(nombreLocalia.equals(null) || nombreLocalia.equals("null")){
			respuesta=false;
			throw new ApplicationException("Nombre Localia institucion vacio");
			
		}else if(!Util.validateNombreApellido(nombreLocalia)){
			respuesta=false;
			throw new ApplicationException("Nombre Localia no valido");
		}
		
		if(direccionLocalia.equals(null) || direccionLocalia.equals("null")){
			respuesta=false;
			throw new ApplicationException("direccion Localia institucion vacio");
			
		}else if(!Util.validateNombreApellido(direccionLocalia)){
			respuesta=false;
			throw new ApplicationException("direccion Localia no valido");
		}
		
		if(nombreDelegado.equals(null) || nombreDelegado.equals("null")){
			respuesta=false;
			throw new ApplicationException("Nombre Delegado institucion vacio");
			
		}else if(!Util.validateNombreApellido(nombreDelegado)){
			respuesta=false;
			throw new ApplicationException("Nombre Delegado no valido");
		}
		
		if(apellidoDelegado.equals(null) || apellidoDelegado.equals("null")){
			respuesta=false;
			throw new ApplicationException("Apellido Delegado institucion vacio");
			
		}else if(!Util.validateNombreApellido(apellidoDelegado)){
			respuesta=false;
			throw new ApplicationException("Apellido Delegado no valido");
		}
		
		if(Util.isNumeric(telefonoDelegado)){
			if(telefonoDelegado.length()>9){
				respuesta=false;
				throw new ApplicationException("Telefono Delegado mayor a 9 ");
			}
			
		}else{
			respuesta=false;
			throw new ApplicationException("Telefono Delegado no valido");
		}
		
		if(!Util.validateEmail(mailDelegado)){
			respuesta=false;
			throw new ApplicationException("Mail Delegado no valido");
		}
		

		
		return respuesta;
	}



	



	
}
