package com.adbansys.generadorBitacora.users;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class serviceUsers {
	
	HashMap<String, Object> data;
	
	@Autowired
	repositoryUsers usuarioRepo;
	
	public List<recordsUsers> inicio(){
		return this.usuarioRepo.findAll();
	}
	
	public ResponseEntity<Object> readUser(Long idGet){
		data = new HashMap<>();
		boolean existence = this.usuarioRepo.existsById(idGet);
		if(!existence) {
			data.put("error", true);
			data.put("message", "No existe un usuario con el Id: " + idGet);
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		data.put("data", usuarioRepo.findById(idGet));
		return new ResponseEntity<>(
				data,
				HttpStatus.ACCEPTED
		);
	}
	
	public ResponseEntity<Object> postUser(recordsUsers body){
		data = new HashMap<>();
		Optional<recordsUsers> mailFound = usuarioRepo.findUserByEmail(body.getEmail());
		if(mailFound.isPresent()) {
			data.put("error", true);
			data.put("message", "Ya existe un usuario con el e-mail: " + body.getEmail());
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		usuarioRepo.save(body);
		data.put("message", "Usuario añadido");
		data.put("data", body);
		return new ResponseEntity<> (
			data,
			HttpStatus.CREATED
		);
	}
	
	public ResponseEntity<Object> deleteUser(Long idDel){
		data = new HashMap<>();
		boolean existence = this.usuarioRepo.existsById(idDel);
		if(!existence) {
			data.put("error", true);
			data.put("message", "No existe un usuario con el Id: " + idDel);
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		data.put("message", "Este usuario con el id " + idDel + " fue eliminado");
		data.put("data", usuarioRepo.findById(idDel));
		usuarioRepo.deleteById(idDel);
		return new ResponseEntity<>(
				data,
				HttpStatus.ACCEPTED
		);
	}
	
	public ResponseEntity<Object> updateUser(Long idP, recordsUsers body) {
		data = new HashMap<>();
		Optional<recordsUsers> idFound = usuarioRepo.findById(idP);
		if(!idFound.isPresent()) {
			data.put("error", true);
			data.put("message", "No existe un usuario con el Id: " + idP);
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		usuarioRepo.save(body);
		data.put("message", "Usuario actualizado");
		data.put("data", body);
		return new ResponseEntity<> (
			data,
			HttpStatus.CREATED
		);
	}
	
	public ResponseEntity<Object> updatePartially(Long idP, String nameP) {
		data = new HashMap<>();
		Optional<recordsUsers> idFound = usuarioRepo.findById(idP);
		if(idFound.isPresent()) {
			recordsUsers body = usuarioRepo.findById(idP).get();
			body.setNombre(nameP);
			usuarioRepo.save(body);
			data.put("message", "Usuario actualizado");
			data.put("data", body);
			return new ResponseEntity<> (
				data,
				HttpStatus.CREATED
			);
		} else {
			data.put("error", true);
			data.put("message", "No existe un usuario con el Id: " + idP);
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
	}
	
	
	
//	public void foundByEmail(recordsUsers RecordsUsers) {
//		Optional<recordsUsers> res = usuarioRepo.findUserByEmail(RecordsUsers.getEmail());
//		if(res.isPresent()) {
//			throw new IllegalStateException("Ya existe el usuario");
//		}
//		usuarioRepo.save(RecordsUsers);
//	}
	
}
