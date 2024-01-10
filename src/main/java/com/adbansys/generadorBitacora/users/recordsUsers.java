package com.adbansys.generadorBitacora.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

public class recordsUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column
	private String level;
	
	@Column
	private String password;
	
	
	public recordsUsers() {
    }
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getData() {
		return "Id: "+id+"; Nombre: "+name+"; E-mail: "+email+"; Nivel: "+level;
	}
	
	public recordsUsers(Long id, String name, String email, String level, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.level= level;
		this.password = password;
	}
}
