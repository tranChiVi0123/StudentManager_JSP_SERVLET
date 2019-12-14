package models.bean;

public class User {
	private String email;
	private String password;
	private int role;
	
	public User(String email, String password, int role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
