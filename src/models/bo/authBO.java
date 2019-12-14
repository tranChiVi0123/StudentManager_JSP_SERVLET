package models.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import models.bean.User;
import models.dao.authDAO;

public class authBO {
	public boolean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
		ArrayList<User> listus = authDAO.getUsersAccount();
		for (User u : listus) {
			if (email.equals(u.getEmail())) {
				if (password.equals(u.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	public void registerBO(String email, String password) throws ClassNotFoundException, SQLException {
		User user = new User(email, password, 0);
		authDAO.registerUserAccount(user);
	}
	
	public boolean checkRole(String email) {
		ArrayList<User> listus = null;
		try {
			listus = authDAO.getUsersAccount();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (User u : listus) {
			if (email.equals(u.getEmail())) {
				if (u.getRole() == 1)
					return true;
			}
		}
		return false;
	}

}
