package com.uleehub.webservice.soap.security;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-7-2
 * <p>
 * Version: 1.0
 */
public class User {
	private String id;
	private String name;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
