package entidad;

public class TipoPersona {
	
	public static final int ADMINISTADOR =1;
	public static final int ENTRENADOR =2;
	public static final int ARBITRO =3;
	public static final int JUGADOR =4;
	
	private int idTipoPersona;
	private String descripcion;
	
	
	public int getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(int idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
