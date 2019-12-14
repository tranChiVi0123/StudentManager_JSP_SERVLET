package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.bean.User;

public class authDAO {
	public static ArrayList<User> getUsersAccount() throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM useraccount";
		ResultSet rs = mysqlHelper.getResultSet(query);
		ArrayList<User> users = new ArrayList<User>();
		while (rs.next()) {
			// System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
			User userpush = new User(rs.getString(1), rs.getString(2), rs.getInt(3));
			users.add(userpush);
		}
		return users;
	}

	public static boolean registerUserAccount(User user) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO useraccount (email, password, role) VALUES ('" + user.getEmail()
				+ "', '" + user.getPassword() + "', '" + user.getRole() + "');\r\n";
		boolean i = mysqlHelper.executeQueryMySQL(query);
		//System.out.println("ADD:" + i);
		return true;
	}
}
