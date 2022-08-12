package project;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionProvider {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshoppingapp","root","");	
			return con;
		} catch (Exception e) {
			
			System.out.println(e+"   NOT Connected");
			return null;
		}
		
	}
}
