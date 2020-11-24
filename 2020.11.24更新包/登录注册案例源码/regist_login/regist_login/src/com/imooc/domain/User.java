package com.imooc.domain;

public class User {
	private String username;
	private String password;
	
	private String path;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", path=" + path + "]";
	}
	
	
}
