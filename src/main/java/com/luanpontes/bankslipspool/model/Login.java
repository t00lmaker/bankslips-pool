package com.luanpontes.bankslipspool.model;

public class Login {
	
	private String username;
	
 	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean checkHashs(int hashUsername, int hashPassword) {
		return username.hashCode() == hashUsername && hashPassword == password.hashCode();
	}
	
	public boolean isComplete() {
		return this.username != null && this.password != null;
	}
}
