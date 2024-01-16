package com.adbansys.generadorBitacora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication /*Control + space*/
@SpringBootApplication(scanBasePackages = {"com.adbasys.generadorBitacora.controller","com.adbasys.generadorBitacora.jwt","com.adbasys.generadorBitacora.users", "com.adbasys.generadorBitacora.xlsx"})
public class GeneradorBitacoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneradorBitacoraApplication.class, args);
	}

}

/* https://start.spring.io/ */