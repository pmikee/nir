package com.pmikee.nir.service;

import org.springframework.data.repository.CrudRepository;

import com.pmikee.nir.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	
	Customer findCustomerById(String id);

}
