package com.pmikee.nir.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmikee.nir.domain.Product;
import com.pmikee.nir.service.ProductRepository;

@RestController
@RequestMapping()
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
    @RequestMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findProductById(id);
    }
    
    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}