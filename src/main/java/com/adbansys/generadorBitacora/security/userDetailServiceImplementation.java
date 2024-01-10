package com.adbansys.generadorBitacora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adbansys.generadorBitacora.users.repositoryUsers;

@Service
public class userDetailServiceImplementation {

	@Autowired
	private repositoryUsers RepositoryUsers;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = RepositoryUsers
						.findUserByEmail(email)
						.orElseThrow(() => new UserNameNotFoundException("No existe el usuario con el email: " + email));
		return new userDetailsImplementation(user);
	}
}
