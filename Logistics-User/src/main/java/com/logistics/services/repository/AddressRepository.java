package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByUserUuid(String userUuid);
	
	Optional<Address> findByAddressUuidAndUserUuid(String addressUuid, String userUuid);
	
	@Query("select a from Address a where a.user.uuid= :userId AND a.isDefault = true")
	Optional<Address> findDefaultAddress(@Param("userId") String userId);
	
	int countUsersByUserUuid(String userUuid);
}
