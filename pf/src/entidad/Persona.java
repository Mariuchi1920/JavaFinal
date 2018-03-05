package entidad;





import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.fabric.xmlrpc.base.Data;

import datos.PersonasDAO;

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
     
     
     public static int buscarPersonaListaRanking(LinkedList<RankingPosiciones> personas ,Persona persona){
 		int respuesta =-1 ;
 		for(int i=0; i<personas.size(); i++){
 			if(personas.get(i).getJugadores().getIdPersona() == persona.getIdPersona()){
 				respuesta= i;
 			}
 		}
 		
 		return respuesta;
 		
 	}
     
    public static boolean  validarPersonaUsuarioContraseña(Persona persona, Persona personaIngresada) throws ApplicationException, SQLException{
    	boolean respuesta = true;
    	String usuario = persona.getUsuario().trim();
		String contraseña = persona.getContraseña().trim();
		if(personaIngresada!=null && personaIngresada.getUsuario().equals(usuario)){
			
			respuesta = true;
		}else if(  !usuario.equals("") && !usuario.equals(null) && !usuario.equals("null") ){
    		if( !contraseña.equals("") && !contraseña.equals(null) && !contraseña.equals("null")){
    			PersonasDAO catPersona = new PersonasDAO();
    			Persona personaBuscada = catPersona.validarUsuarioPersona(usuario);
    			if(personaBuscada!=null){
    				respuesta=false;
    				throw new ApplicationException("Usuario ya existe");
    			}
    			
    			
    		}else{
    			respuesta=false;
    			throw new ApplicationException("Debe ingresar una contraseña");
    		}
    		
    	}else if( !contraseña.equals("") && !contraseña.equals(null) && !contraseña.equals("null")){
    		respuesta=false;
    		throw new ApplicationException("Debe ingresar un usuario");
    	}else if(persona.getTipoPersona().getIdTipoPersona()== TipoPersona.ADMINISTADOR){
    		respuesta=false;
    		throw new ApplicationException("Debe ingresar un usuario y contraseña si es administrador");
    	}
    	
    	
		return respuesta;
    	
    	
    	
    }
     
	public static boolean validarPersona(Persona persona) throws ApplicationException {
		// TODO Auto-generated method stub
		boolean respuesta= true;
		String nombre = persona.getNombre().trim();
		String apellido = persona.getApellido().trim();
		String numeroDocumento = persona.getNumeroDocumento().trim();
		String mail = persona.getMail().trim();
		String telefono = persona.getTelefono().trim();
		Date fechaNacimiento = persona.getFechaNacimiento();
		
		
		if(nombre.equals(null) || nombre.equals("null")){
			
			respuesta= false;
			throw new ApplicationException("Ingresar un valor al campo nombre");
		}else if(!Util.validateNombreApellido(nombre)){
			respuesta= false;
			throw new ApplicationException("Nombre ingresado no es valido");
		} 
			
		
		
		
		if(apellido.equals(null) || apellido.equals("null")){
			
			respuesta= false;
			throw new ApplicationException("Ingresar un valor al campo apellido");
		}else if(!Util.validateNombreApellido(apellido)){
			respuesta= false;
			throw new ApplicationException("Apellido ingresado no es valido");
		} 
		
		
		if(telefono.equals(null) || telefono.equals("") ){
			respuesta= false;
			throw new ApplicationException("Ingresar un valor al campo telefono");
			
		}else if(!Util.isNumeric(telefono)){
			respuesta= false;
			throw new ApplicationException("Telefono contiene caracteres especiales");
		}else if(telefono.length()<6 || telefono.length()>15){
			respuesta= false;
			throw new ApplicationException("Telefono no es de logitud valida (mayor a 6 y menos a 15)");
		}
		
		
		
		if(!numeroDocumento.equals(null) || !numeroDocumento.equals("null") || !numeroDocumento.equals("") ){
			if(!Util.isNumeric(numeroDocumento)){
				respuesta= false;
				throw new ApplicationException("Numero documento contiene caracteres especiales");
			}else if(numeroDocumento.length()>9){
				respuesta= false;
				throw new ApplicationException("Numero documento es de longitud incorrecta");
			}
			
		}else {
			respuesta= false;
			throw new ApplicationException("Ingresar un valor al campo numero Documento");
		}
		
		if(mail!=null && mail!=""){
			if(!Util.validateEmail(mail)){
				respuesta= false;
				throw new ApplicationException("Mail es incorrecto");
			}
		}else {
			respuesta= false;
			throw new ApplicationException("Ingresar un valor al mail");
		}
		if(fechaNacimiento!=null){
			if(!Util.compararFechaConHoy(fechaNacimiento)){
				respuesta= false;
				throw new ApplicationException("Fecha incorrecta");
			}
		}else{
			respuesta= false;
			throw new ApplicationException("Ingresar un valor en fecha Nacimiento");
		}
		
		
		return respuesta;
		
		
	} 
	
	
}
