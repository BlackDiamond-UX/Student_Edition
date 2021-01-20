package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionClass {

	private static final String url ="jdbc:mysql://localhost:3306/gestion_des_etudiants";
	private static final String user ="root";
	private static final String password ="";
	public static Connection getConnection()throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);
			return con;
		} catch ( ClassNotFoundException | SQLException e ) {
			Logger.getLogger(ConnectionClass.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}
}
	
