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
	
	public Optional<recordsUsers> readUser(Long idU){
		return usuarioRepo.findById(idU);
	}
	
	public recordsUsers postUser(recordsUsers body){
		body = usuarioRepo.save(body);
		return body;
	}
	
	public ResponseEntity<Object> deleteUser(Long idDel){
		data = new HashMap<>();
		boolean existence = this.usuarioRepo.existsById(idDel);
		if(!existence) {
			data.put("error", true);
			data.put("message", "No existe un usuario con ese Id");
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		usuarioRepo.deleteById(idDel);
		data.put("message", "El usuario con el id " + idDel + " fue eliminado");
		return new ResponseEntity<>(
				data,
				HttpStatus.ACCEPTED
		);
		
	}
	
//	public recordsUsers updateUser(Long idP, String nameP) {
//		usuarioRepo.findById(idP).get().setNombre(nameP);
//		return usuarioRepo.save(null);
//	}
	
	public recordsUsers updateUser(Long idP, recordsUsers body) {
		body = usuarioRepo.save(body);
		return body;
	}
	
//	public void foundByEmail(recordsUsers RecordsUsers) {
//		Optional<recordsUsers> res = usuarioRepo.findUserByEmail(RecordsUsers.getEmail());
//		if(res.isPresent()) {
//			throw new IllegalStateException("Ya existe el usuario");
//		}
//		usuarioRepo.save(RecordsUsers);
//	}
	
}
