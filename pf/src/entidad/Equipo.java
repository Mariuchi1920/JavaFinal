package entidad;



public class Equipo {
	//private int idEquipo;
	private Categoria idCategorias;
	private Institucion idInstitucion;
	private String nombreEquipo;
	private Persona idEntrenador;
	public Categoria getIdCategorias() {
		return idCategorias;
	}
	public void setIdCategorias(Categoria idCategorias) {
		this.idCategorias = idCategorias;
	}
	public Institucion getIdInstitucion() {
		return idInstitucion;
	}
	public void setIdInstitucion(Institucion idInstitucion) {
		this.idInstitucion = idInstitucion;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	public Persona getIdEntrenador() {
		return idEntrenador;
	}
	public void setIdEntrenador(Persona idEntrenador) {
		this.idEntrenador = idEntrenador;
	}
	
	
	}
