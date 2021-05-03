package com.example.GeoKidsBackend.payload;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String firstName;
	private String lastName;
	private String city;
	private String email;
	private List<String> roles;


	public JwtResponse(String token, String id, String firstName, String lastName, String city,
			String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String accessToken) {
		this.token = accessToken;
	}

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}
}
