package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectFA {
	private static Connection con;
	private static String connectionURL = "jdbc:mysql://localhost:3306/FireAlarmDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	static PreparedStatement stmt;
	
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(connectionURL, user, password);
		}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return con;
	}
	
}
