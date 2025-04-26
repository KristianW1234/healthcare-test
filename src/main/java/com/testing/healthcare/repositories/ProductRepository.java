package com.testing.healthcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testing.healthcare.entities.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value="SELECT p.* FROM Product p WHERE p.id = :id", nativeQuery = true)
	public Product findProductById(@Param("id") Long id);
	
	@Query(value="SELECT p.* FROM Product p WHERE p.product_sku = :sku", nativeQuery = true)
	public Product findProductBySku(@Param("sku") String sku);
}
