package entidad;



public class Equipo {
	//private int idEquipo;
	private Categoria categorias;
	private Institucion institucion;
	private String nombreEquipo;
	private Persona entrenador;
	public Categoria getCategorias() {
		return categorias;
	}
	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	public Persona getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(Persona entrenador) {
		this.entrenador = entrenador;
	}
	
	
	
	
	
	}
