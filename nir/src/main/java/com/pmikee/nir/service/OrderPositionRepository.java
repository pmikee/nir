package com.pmikee.nir.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmikee.nir.domain.OrderPosition;

public interface OrderPositionRepository extends JpaRepository<OrderPosition, String>{
	
}
