package com.adbansys.generadorBitacora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication /*Control + space*/
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// @SpringBootApplication(scanBasePackages = {"com.adbasys.generadorBitacora.controller","com.adbasys.generadorBitacora.jwt","com.adbasys.generadorBitacora.users", "com.adbasys.generadorBitacora.xlsx"})
@SpringBootApplication(scanBasePackages = {"com.adbasys.generadorBitacora.controller","com.adbasys.generadorBitacora.jwt","com.adbasys.generadorBitacora.users", "com.adbasys.generadorBitacora.xlsx"}, exclude = {DataSourceAutoConfiguration.class})
public class GeneradorBitacoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneradorBitacoraApplication.class, args);
	}

}

/* https://start.spring.io/ */