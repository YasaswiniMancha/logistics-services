package com.logistics.services.repository;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByUuid(String categoryUuid);
	
	Optional<Category> findByName(String categoryName);
	
	List<Category> findByParentIsNull();
	
	List<Category> findByParentUuid(String parentUuid);
	
}
