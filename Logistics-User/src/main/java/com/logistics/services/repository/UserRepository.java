package com.logistics.services.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Roles;
import com.logistics.services.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUUID(String uuid);

	Optional<User> findByEmail(String Email);

	Optional<User> findByPhone(String phone);

	boolean existByPhone(String phone);

	boolean existByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.role = :role AND u.status = 'ACTIVE'")
	List<User> findActiveUsersByRole(@Param("role") Roles role);

	@Query("SELECT u from User u WHERE u.status = 'ACTIVE' AND u.deleted= false")
	Page<User> findAllActive(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.otpExpiresAt <now AND u.status = 'UNVERIFIED'")
	List<User> findUnverifiedWithExpiredOtp(@Param("now") LocalDateTime now);
}
