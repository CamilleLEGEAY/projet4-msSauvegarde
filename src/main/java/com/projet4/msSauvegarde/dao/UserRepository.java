package com.projet4.msSauvegarde.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projet4.msSauvegarde.modele.User;


public interface UserRepository extends CrudRepository<User, Integer>{

	
	Optional<User> findByTokenUserName(String emailId);
	
}
