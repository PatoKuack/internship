package com.adbansys.generadorBitacora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adbansys.generadorBitacora.xlsx.recordEmployee;
import com.adbansys.generadorBitacora.xlsx.serviceEmployee;

@RestController
@RequestMapping("/bitacora")
public class xlsxController {
	
	private final serviceEmployee services;
	@Autowired
	public xlsxController(serviceEmployee services) {
		this.services = services;
	}
	
	@GetMapping("/")
    public ResponseEntity<Object> inicio() {
        return services.inicio();
    }
    
    @GetMapping("/bitacora")
    public ResponseEntity<Object> generateExcel() {
        return services.generateExcel();
    }
    
    @GetMapping("/proeba")
    public String proeba() {
    	return "Hello";
    }
	
	
	@PutMapping(
			path = "/putrecord/{yearR}/{monthR}/{dayR}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Object> updateRecord(@PathVariable(value="yearR") int yearR, @PathVariable(value="monthR") int monthR, @PathVariable(value="dayR") int dayR, @RequestBody recordEmployee body) {
		return services.updateRecord(yearR, monthR, dayR, body);
	}
}

// Curso para el uso de POI
// https://www.youtube.com/watch?v=retGxru3n4I&list=PLr23_YfwEbPQ6LlBhef5D18YT-IPD1n7y&index=4

// Curso para autenticación
// https://www.youtube.com/watch?v=aqCvtufXdso

// ejecutable .jar
// https://www.youtube.com/watch?v=qDTUYkaXAEc
// cd C:\Users\noctu\git\repository\generadorBitacora\target
// java -jar generadorBitacora-0.0.1-SNAPSHOT.jar
// localhost:8080/bitacora/
