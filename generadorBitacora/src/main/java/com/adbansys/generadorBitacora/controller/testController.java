package com.adbansys.generadorBitacora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adbansys.generadorBitacora.users.recordsUsers;
import com.adbansys.generadorBitacora.users.serviceUsers;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testservice")
public class testController {
	
	private final serviceUsers usuarioServicio;
	@Autowired
	public testController(serviceUsers usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}
	
	List<recordsUsers> listaUsuarios = new ArrayList<recordsUsers>();
	
	@GetMapping("/hola")
    public String hola() {
        return this.usuarioServicio.proeba();
    }
	
	@GetMapping("/")
	public List<recordsUsers> inicio(){
		return usuarioServicio.inicio();
	}
	
	@GetMapping("/uget/{idU}")
	@ResponseBody
	public Optional<recordsUsers> readUser(@PathVariable(name="idU") Long idU){
		return this.usuarioServicio.readUser(idU);
	}
	
	@PostMapping(
			path = "upost",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public recordsUsers Usuario(@RequestBody recordsUsers body){
		return usuarioServicio.postUser(body);
	}
	
	@DeleteMapping("/udelete/{idDel}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name="idDel") Long idDel){
		return this.usuarioServicio.deleteUser(idDel);
	}
	
//	@PatchMapping("/upatch/{idP}/{nameP}")
//	public recordsUsers updatePartially(@PathVariable Long idP, @PathVariable String nameP) {
//		return usuarioServicio.updateUser(idP, nameP);
//	}
	
	@PutMapping(
			path = "uput/{idP}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public recordsUsers updatePartially(@PathVariable(value="idP") Long idP, @RequestBody recordsUsers body) {
		return this.usuarioServicio.updateUser(idP, body);
	}
	
	// https://www.javatpoint.com/java-arraylist
	// https://jarroba.com/arraylist-en-java-ejemplos/
	// https://www.javaguides.net/2018/11/spring-getmapping-postmapping-putmapping-deletemapping-patchmapping.html
	// https://www.youtube.com/watch?v=mNwo-sm4Yz8

}


// https://docs.oracle.com/javase/7/docs/api/
// https://start.spring.io/
// https://mvnrepository.com/
// https://blog.fileformat.com/spreadsheet/create-excel-workbook-in-java-using-apache-poi/
// https://blog.fileformat.com/presentation/apache-poi-java-library-for-microsoft-office-file-formats/
// https://jarroba.com/arraylist-en-java-ejemplos/

// creación de CRUD
// https://www.youtube.com/watch?v=6tWtNYsqXL4
