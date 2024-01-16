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
	private String rol;
	
	@Column
	private String password;

	@Column
	private boolean authorization;
	
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getAuthorization() {
		return authorization;
	}

	public void setAuthorization(boolean authorization) {
		this.authorization = authorization;
	}
	
	public String getData() {
		return "Id: "+id+"; Nombre: "+name+"; E-mail: "+email+"; Rol: "+rol+"; Autorizado: "+authorization;
	}
	
	public recordsUsers(Long id, String name, String email, String rol, String password, boolean authorization) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.rol= rol;
		this.password = password;
		this.authorization = authorization;
	}
}
