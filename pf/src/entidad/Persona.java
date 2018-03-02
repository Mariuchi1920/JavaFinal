package entidad;





import java.sql.Date;
import java.util.LinkedList;

import com.mysql.fabric.xmlrpc.base.Data;

public class Persona {
	
	private int idPersona;
	private String nombre;
	private String apellido;
	private String telefono;
	private Date fechaNacimiento;
	private String tipoDocumento;
	private String numeroDocumento;
	private String mail;
	private String usuario;
	private String contraseña;
	private TipoPersona tipoPersona;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	public boolean isAdmin(){
		boolean respuesta = false;
		if(tipoPersona.getIdTipoPersona()==TipoPersona.ADMINISTADOR){
			respuesta= true;
		}
		return respuesta;
		
		
	}
	
	
	public static LinkedList<Persona> eliminarPersonas (LinkedList<Persona> personas ,Persona persona){
		
		for(int i=0; i<personas.size(); i++){
			if(personas.get(i).getIdPersona() == persona.getIdPersona()){
				personas.remove(i);
			}
		}
		
		return personas;
		
	}
	
	
     public static boolean buscarPersona(LinkedList<Persona> personas ,Persona persona){
		boolean respuesta =false ;
		for(int i=0; i<personas.size(); i++){
			if(personas.get(i).getIdPersona() == persona.getIdPersona()){
				respuesta= true;
			}
		}
		
		return respuesta;
		
	} 
	
	
}
