package com.dao.foodApplication.model;

public class User {
	
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	private String address;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public User() {
		super();
	}
	public User(String userName, String email, String password, String address) {
		super();
//		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
//		this.confirmPassword = confirmPassword;
		this.address = address;
	}
	@Override
	public String toString() {
		return userId + "      " + userName + "    " + email + "    " + password
				+ "    " + confirmPassword + "     " + address;
	}
	
	

}
