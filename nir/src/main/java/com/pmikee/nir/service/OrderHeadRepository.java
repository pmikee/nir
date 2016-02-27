package com.pmikee.nir.service;

import org.springframework.data.repository.CrudRepository;

import com.pmikee.nir.domain.OrderHead;

public interface OrderHeadRepository extends CrudRepository<OrderHead, String>{

	OrderHead findOrderById(String id);
}
