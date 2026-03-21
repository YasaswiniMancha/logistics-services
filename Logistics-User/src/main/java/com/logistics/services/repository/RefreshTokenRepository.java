package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository {

	Optional<RefreshToken> findByToken(String token);
	
	Optional<RefreshToken> findByUserUuid(String userUuid);
	
	void deleteUserByUuid(String userUuid);
	
	@Query("delete from RefreshToken r where r.expiresAt < :now")
	void deleteAllExpired(@Param("now") LocalDateTime now);
	
}
