package com.adbansys.generadorBitacora.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.lang.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		
		authCredentials AuthCredentials  = new authCredentials();
		
		try {
			authCredentials = new ObjectMapper().readvalue(request.getReader(), AuthCredentials.class)
		} catch(IOException e) {}
	
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				AuthCredentials.getEmail(),
				AuthCredentials.getPasword(),
				Collections.emptyList()
				);
		
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	@Override
	protected void successfullAuthentication(HttpServletRequest request, 
											HttpServletResponse, response,
											FilterChain, chain,
											Authentication, authResult) throws IOException ServletException {
		
		userDetailsImplementation (UserDetails) = (userDetailsImplementation) authResult.getPrincipal(); 
		String token = tokenUtils.createToken(userDetails.getUsername(), userDetails.getEmail());
	
		// https://youtu.be/_p-Odh3MZJc?si=89qExt9ADdoITWL4&t=1680
	}
	
}
