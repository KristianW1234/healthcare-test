package com.testing.healthcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testing.healthcare.entities.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Query(value="SELECT c.* FROM Category c WHERE c.id = :id", nativeQuery = true)
	public Category findCategoryById(@Param("id") Long id);
	
	
}
