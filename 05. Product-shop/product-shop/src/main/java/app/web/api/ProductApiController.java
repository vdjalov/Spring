package app.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.repository.ProductRepository;
import app.web.models.ProductDetailsView;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public ProductApiController(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@GetMapping(value = "/products", produces = "application/json")
	public List<ProductDetailsView>getAllProducts() {
	return	this.productRepository.findAll().stream()
			.map(product -> this.modelMapper.map(product, ProductDetailsView.class))
			.collect(Collectors.toList());
	}
}
