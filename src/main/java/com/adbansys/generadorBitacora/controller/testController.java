package com.adbansys.generadorBitacora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adbansys.generadorBitacora.users.recordsUsers;
import com.adbansys.generadorBitacora.users.serviceUsers;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testservice")
public class testController {
	
	private final serviceUsers usuarioServicio;
	@Autowired
	public testController(serviceUsers usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}
	
	@GetMapping("/")
	public List<recordsUsers> inicio(){
		return usuarioServicio.inicio();
	}
	
	@GetMapping("/uget/{idGet}")
	@ResponseBody
	public ResponseEntity<Object> readUser(@PathVariable(name="idGet") Long idGet){
		return this.usuarioServicio.readUser(idGet);
	}
	
//	@GetMapping("/login/{email}/{password}")
//	@ResponseBody
//	public ResponseEntity<Object> login(@PathVariable String email, @PathVariable String password){
//		return this.usuarioServicio.login(email, password);
//	}
	@PostMapping(
			path = "login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Object> Usuario(@RequestBody(required = true) Map<String, String> requestMap){
		return usuarioServicio.login(requestMap);
	}
	
	@PostMapping(
			path = "upost",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Object> Usuario(@RequestBody recordsUsers body){
		return usuarioServicio.postUser(body);
	}
	
	@DeleteMapping("/udelete/{idDel}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name="idDel") Long idDel){
		return this.usuarioServicio.deleteUser(idDel);
	}
	
	@PutMapping(
			path = "uput/{idP}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Object> updatePartially(@PathVariable(value="idP") Long idP, @RequestBody recordsUsers body) {
		return this.usuarioServicio.updateUser(idP, body);
	}
	
	@PatchMapping("/upatch/{idP}/{nameP}")
	public ResponseEntity<Object> updatePartially(@PathVariable Long idP, @PathVariable String nameP) {
		return usuarioServicio.updatePartially(idP, nameP);
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
// https://www.youtube.com/watch?v=M7lhQMzzHWU

// autenticación y tokens
// https://www.youtube.com/watch?v=ZzpDyIJizjo

// github
// https://www.youtube.com/watch?v=wYzkZn0qYv8
// Problemas con autenticación de GitHub:
// https://www.youtube.com/watch?v=gO20QGT6aW8

// ejecutable .jar
// https://www.youtube.com/watch?v=qDTUYkaXAEc
// cd C:\Users\noctu\git\repository\generadorBitacora\target
// java -jar generadorBitacora-0.0.1-SNAPSHOT.jar
