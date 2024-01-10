package com.adbansys.generadorBitacora.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryUsers extends JpaRepository<recordsUsers, Long> {

	Optional<recordsUsers> findUserByEmail(String email); // findOneByEmail
	
}
