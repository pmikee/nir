package com.pmikee.nir.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmikee.nir.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	Product findProductById(String id);
	
}
