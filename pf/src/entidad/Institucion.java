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



	



	
}
