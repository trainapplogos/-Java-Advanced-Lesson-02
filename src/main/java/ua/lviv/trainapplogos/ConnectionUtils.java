package ua.lviv.trainapplogos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
//	private static String DB_URL = "jdbc:mysql://localhost:3306/library2";
	private static String DB_URL = "jdbc:mysql://localhost/library2";
	private static String user = "root";
	private static String password = "12345678";
	
	public static Connection OpenConnection() throws SQLException   {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance(); //load driver
		}
		catch (Exception ex) {
			System.out.println("Connection failed...");
            System.out.println(ex);
		}
		return DriverManager.getConnection(DB_URL, user, password); //open connection to DB
	}
}
