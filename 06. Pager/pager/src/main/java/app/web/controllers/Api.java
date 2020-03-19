package app.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.data.models.Product;
import app.data.repositories.ProductRepository;

@RestController
public class Api {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping(value = "/api/products", produces = "application/json")
	public Page<Product> getProducts(@RequestParam Optional<Integer> page, 
									 @RequestParam Optional<String> sortBy) {
		Page<Product> products = this.productRepository.findAllProducts(PageRequest.of(page.orElse(0), 5, Sort.by(Direction.ASC, sortBy.orElse("id"))));
		return products;
	}
	
}
