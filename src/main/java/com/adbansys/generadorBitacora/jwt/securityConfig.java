package com.adbansys.generadorBitacora.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class securityConfig {

	@Autowired
	private userDetailsService UserDetailsService;
	
	@Autowired
	private jwtFilter JwtFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	// permitir que otros dominios obtengan datos de este proyecto
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
							.and()
							.csrf().disable() // spring tiene sus propios mecanismos antihackers, así que no es útil este mecanismo y se deshabilita
							.authorizeHttpRequests()
							.antMatchers("/testservice/upost", "/testservice/login") // /testservice/* -> acepta todas las rutas del dominio
							.permitAll() // se permitiran solo las rutas colocadas en antMatchers
//							.anyRequest() // para cualquier otra ruta se deben autenticar
//							.authenticated()
							.and().exceptionHandling()
							.and().sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
