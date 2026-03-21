package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.services.entity.Carrier;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {

	Optional<Carrier> findByCode(String code);

	List<Carrier> findByIsActiveTrue();

}
