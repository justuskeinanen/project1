package com.example.project1.domain;

import javax.validation.constraints.Size;

public class UserCheck {

	@Size(min = 4, max = 50, message = "4-50 characters")
	private String username = "";

	@Size(min = 4, max = 50, message = "4-50 characters")
	private String password = "";

	@Size(min = 4, max = 50, message = "4-50 characters")
	private String passwordCheck = "";

	private String role = "USER";

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
