package com.logistics.works.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.works.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByUsername(String usernameOrEmail);
	
	Optional<User> findByEmail(String Email);
	
	boolean existByUsername(String username);
	
	boolean existByEmail(String email);

}
