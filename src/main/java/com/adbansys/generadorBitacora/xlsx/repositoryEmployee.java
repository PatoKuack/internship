package com.adbansys.generadorBitacora.xlsx;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryEmployee extends JpaRepository<recordEmployee, Long> {
	// findRecordByDate ---- ERROR - si se usa DATE no encontrará la variable fecha en recordEmployee
	// findRecordByFecha ---- CORRECTO
	Optional<recordEmployee> findRecordByFecha(LocalDate fecha);
	

	// Optional<recordEmployee> findRecordById(Long id);
}
