package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlHelper {
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionString = "jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=yes&characterEncoding=UTF-8";
		String usernameDB = "root";
		String passwdDB = "";
		Connection conn = DriverManager.getConnection(connectionString, usernameDB, passwdDB);
		return conn;
	}
	public static ResultSet getResultSet(String query) throws ClassNotFoundException, SQLException{
		mysqlHelper my = new mysqlHelper();
		Connection conn = my.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	public static boolean executeQueryMySQL(String query) throws ClassNotFoundException, SQLException {
		mysqlHelper my = new mysqlHelper();
		Connection conn = my.getConnection();
		Statement statement = conn.createStatement();
		return statement.execute(query);
	}

}
