package entidad;



public class Categoria {




	int idCategorias;
	String descripcion;
	TipoEstado estado;
	
	/*public Categoria(int idcateogria, String descripcion, int estado) {
		this.idcateogria = idcateogria;
		this.descripcion = descripcion;
		this.estado = estado;
	}*/

	
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
	
/*	
	
	public boolean registrarNuevaCategoria(int idcat, String descripcion, int estado) throws SQLException{
		Conexion con= new Conexion();
		PreparedStatement pst= null;
		try {
			String consulta= "insert into categorias ( idCategorias, descripcion, estado)values (?,?,?)";
			pst=con.getConexion().prepareStatement(consulta);
			pst.setInt(1,idcat);
			pst.setString(2, descripcion);
			pst.setInt(3,estado);
			
			//usa el executeUpdate para ingresar datos a la base de datos 
			if(pst.executeUpdate()==1){
				return true;
				
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			con.getConexion().close();
			pst.close();
		}
		return false;
		
	}

	
	public static LinkedList<Categoria> getCategorias() throws SQLException{
		LinkedList<Categoria>listaCategorias= new LinkedList<Categoria>();
		Conexion con= new Conexion();
		Statement st = null;
		ResultSet rs=null;
		try{
			 st= con.getConexion().createStatement();
			 rs= st.executeQuery("select * from categorias");
			while(rs.next()){
				Categoria cat= new Categoria();
				
				cat.setIdcateogria(rs.getInt(1));
				cat.setDescripcion(rs.getString(2));
				TipoEstado tipoEstado = TipoEstado.getTipoEstados(rs.getInt(3));
				cat.setEstado(tipoEstado);
				listaCategorias.add(cat);
						
			}
			
		}catch(Exception e){
			// ver que va aca adentro averiguar.... 
		}finally {
			st.close();
			rs.close();
			con.getConexion().close();
			
			
		}
		return listaCategorias;
		
	}
	

	
	
	public void eliminarCategoria(int idCat){
		Conexion con= new Conexion();
		Statement st=null;
				
		String lista= "delete from categorias where idCategorias= "+idCat+";";
		
		try {
			
			st=  con.getConexion().createStatement();
			int i=st.executeUpdate(lista);
			
			st.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}*/
	}
