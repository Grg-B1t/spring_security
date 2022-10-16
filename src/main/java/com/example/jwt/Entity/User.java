package com.example.jwt.Entity;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	@Id
	private String username;
	private String password;
	private String userFirstName;
	private String userLastName;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
		joinColumns = {
				@JoinColumn(name = "USER_ID")
		},
		inverseJoinColumns = {
				@JoinColumn(name = "ROLE_ID")
	}
			)
	private Set<Role> role;

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

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	

}
