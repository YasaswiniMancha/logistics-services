package com.logistics.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.services.entity.CustomerTier;
import com.logistics.services.entity.TierPricing;

@Repository
public interface TierPricingRepository  extends JpaRepository<TierPricing, Long>  {

	List<TierPricing> findByProductUuid(String ProductUuid);
	
	Optional<TierPricing> findByProductUuidAndTierType(String ProductUuid, CustomerTier tier);
	
}
