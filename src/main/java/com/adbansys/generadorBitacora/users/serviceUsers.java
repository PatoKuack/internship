package com.adbansys.generadorBitacora.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adbansys.generadorBitacora.jwt.jwtUtil;
import com.adbansys.generadorBitacora.jwt.userDetailsService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class serviceUsers {
	
	HashMap<String, Object> data;
	
	@Autowired
	repositoryUsers usuarioRepo;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
 
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private userDetailsService UserDetailsService;
    
    @Autowired
    private jwtUtil JwtUtil;
    
//    @Override
//    public ResponseEntity<Object> login(String mail, String pass){
//    	data = new HashMap<>();
//    	try {
//    		Authentication authentication = authenticationManager.authenticate(
//    				new UsernamePasswordAuthenticationToken(mail, pass));
//    		
//    		if(authentication.isAuthenticated()) {
//    			if(UserDetailsService.getUserDetail().get().getAuthorization() == true) {
//    				data.put("message", "token: " + JwtUtil.generateToken(UserDetailsService.getUserDetail().get().getEmail(), UserDetailsService.getUserDetail().get().getRol()));
//    				return new ResponseEntity<>(
//    						data,
//    						HttpStatus.ACCEPTED
//    				);
//    			} else {
//    				data.put("error", true);
//    				data.put("message", "Necesita que el administrador le de autorización");
//    				return new ResponseEntity<>(
//    						data,
//    						HttpStatus.UNAUTHORIZED
//    				);
//    			}
//    		}
//    	} catch(Exception exception) {
//    		data.put("error", true);
//			data.put("message", exception);
//			return new ResponseEntity<>(
//					data,
//					HttpStatus.BAD_REQUEST
//			);
//    	}
//    	data.put("error", true);
//		data.put("message", "Credenciales incorrectas");
//		return new ResponseEntity<>(
//				data,
//				HttpStatus.UNAUTHORIZED
//		);
//    }

    public ResponseEntity<Object> login(Map<String, String> requestMap){
    	data = new HashMap<>();
    	try {
    		Authentication authentication = authenticationManager.authenticate(
    				new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
    		
    		if(authentication.isAuthenticated()) {
    			if(UserDetailsService.getUserDetail().get().getAuthorization() == true) {
    				data.put("message", "token: " + JwtUtil.generateToken(UserDetailsService.getUserDetail().get().getEmail(), UserDetailsService.getUserDetail().get().getRol()));
    				return new ResponseEntity<>(
    						data,
    						HttpStatus.ACCEPTED
    				);
    			} else {
    				data.put("error", true);
    				data.put("message", "Necesita que el administrador le de autorización");
    				return new ResponseEntity<>(
    						data,
    						HttpStatus.UNAUTHORIZED
    				);
    			}
    		}
    	} catch(Exception exception) {
    		data.put("error", true);
			data.put("message", exception);
			return new ResponseEntity<>(
					data,
					HttpStatus.BAD_REQUEST
			);
    	}
    	data.put("error", true);
		data.put("message", "Credenciales incorrectas");
		return new ResponseEntity<>(
				data,
				HttpStatus.UNAUTHORIZED
		);
    }
    
    public serviceUsers(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
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
		String passwordEncripted = passwordEncoder.encode(body.getPassword());
		body.setPassword(passwordEncripted);
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
			body.setName(nameP);
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
