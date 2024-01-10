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
	private String nombre;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column
	private int edad;
	
	
	public recordsUsers() {
    }
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
//	public String getData() {
//		return "Id: "+id+"; Nombre: "+nombre+"; E-mail: "+email+"; Edad: "+edad;
//	}
	
	public recordsUsers(Long id, String name, String mail, int age) {
		this.id = id;
		nombre = name;
		email = mail;
		edad = age;
	}
}
