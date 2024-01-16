package com.adbansys.generadorBitacora.jwt;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.adbansys.generadorBitacora.users.recordsUsers;
import com.adbansys.generadorBitacora.users.repositoryUsers;

import jdk.internal.org.jline.utils.Log;

@Slf4j
@Service
public class userDetailsService implements UserDetailsService {

	@Autowired
	private repositoryUsers RepositoryUsers; //DAO
	
	private Optional<recordsUsers> RecordsUsers; //POJO
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Log.info("Dentro de loadUserByUsername {}", username);
		RecordsUsers = RepositoryUsers.findUserByEmail(username);
		
		if(!Objects.isNull(RecordsUsers)) {
			return new User(RecordsUsers.get().getEmail(), RecordsUsers.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

	public Optional<recordsUsers> getUserDetail() {
		return RecordsUsers;
	}
}
