package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
public class Conexion {

	public String userDb="root";
	public String passDb= "";
	public String host= "localhost";
	public String port= "3306";
	public String dataBases= "torneo";
	
	
	
	private Connection con;
	
	public Conexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*con=DriverManager.getConnection("jdbc:mysql://host:port/dataBase",userName,password);*/
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/torneo",userDb, passDb);
			System.out.println("Se ha conectado a la base:"+ dataBases);
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR"+e);
			// TODO: handle exception
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error maria");
			
		}
	
		
	}
public Connection getConexion(){
	return con;
}
			
/*
public static void main(String[] args) {
			Conexion con= new Conexion();
			
			}*/
}
