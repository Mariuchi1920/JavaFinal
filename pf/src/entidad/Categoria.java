package entidad;



public class Categoria {




	int idCategorias;
	String añoCategoria;
	String descripcion;
	TipoEstado estado;
	


	
	public int getIdCategorias() {
		return idCategorias;
	}

	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}

	public String getAñoCategoria() {
		return añoCategoria;
	}

	public void setAñoCategoria(String añoCategoria) {
		this.añoCategoria = añoCategoria;
	}
	

	}
