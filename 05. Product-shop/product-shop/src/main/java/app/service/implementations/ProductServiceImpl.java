package app.service.implementations;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.data.models.Category;
import app.data.models.Product;
import app.data.repository.CategoryRepository;
import app.data.repository.ProductRepository;
import app.service.CloudinaryService;
import app.service.ProductService;
import app.service.models.AddProductServiceModel;
import app.service.models.EditProductServiceModel;
import app.web.models.FindAllProductsView;
import app.web.models.ProductDetailsView;


@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	private CloudinaryService cloudinaryService;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.cloudinaryService = cloudinaryService;
		this.categoryRepository = categoryRepository;
	}


	@Override
	public void saveProduct(AddProductServiceModel addProductServiceModel) throws IOException {
		Set<Category> allSelectedCategories = 
			  addProductServiceModel.getCategories().stream()
									.map(catName -> {
										Category category = this.categoryRepository.findByName(catName);
										return category;
									})
									.collect(Collectors.toSet());
																	
		Product product = this.modelMapper.map(addProductServiceModel, Product.class);		
		String imageUrl = this.cloudinaryService.uploadImage(addProductServiceModel.getImage());
		product.setImageUrl(imageUrl);
		product.setCategories(allSelectedCategories);
		this.productRepository.save(product);
	}


	@Override
	public List<FindAllProductsView> getAllProducts() {
		List<FindAllProductsView> products = this.productRepository.findAll().stream()
																   .map(product-> this.modelMapper.map(product, FindAllProductsView.class))
																   .collect(Collectors.toList());
		return products;
	}


	@Override
	public ProductDetailsView getProductById(int id) {
		ProductDetailsView productDetailsView = this.modelMapper.map(this.productRepository.findById(id).get(), ProductDetailsView.class);
		return productDetailsView;
	}


	@Override
	public void amendProduct(EditProductServiceModel editProductServiceModel, int id) {
		Product product = this.productRepository.findById(id).get();
		product.setDescription(editProductServiceModel.getDescription());
		product.setName(editProductServiceModel.getName());
		product.setPrice(editProductServiceModel.getPrice());
		product.setCategories(editProductServiceModel.getCategories().stream().map(cat -> {
			Category category = this.categoryRepository.findByName(cat);
			return category;
		})
		.collect(Collectors.toSet()));
		this.productRepository.save(product);
	}


	@Override
	public void deleteProductById(int id) {
		this.productRepository.deleteById(id);
		
	}



}
