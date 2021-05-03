package com.example.GeoKidsBackend.payload;

import java.util.Set;


import com.example.GeoKidsBackend.model.Role;

public class AdminSignUpRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String city;
    private Set<String> roles;
    
    public AdminSignUpRequest() {}
	public AdminSignUpRequest(String firstName, String lastName, String email, String password, String city,
			Set<String> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.roles = roles;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

    
    
}
