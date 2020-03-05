package app.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Category;
import app.data.repository.CategoryRepository;
import app.service.CategoryService;
import app.service.models.GetCategoryServiceModel;
import app.web.models.CategoryViewModel;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public void addCategory(GetCategoryServiceModel getCategoryServiceModel) {
		Category category = this.modelMapper.map(getCategoryServiceModel, Category.class);
		this.categoryRepository.save(category);
	}


	@Override
	public List<CategoryViewModel> findAllCategories() {
		return this.categoryRepository.findAll()
				.stream()
				.map(cat -> this.modelMapper.map(cat, CategoryViewModel.class))
				.collect(Collectors.toList());
	}


	@Override
	public CategoryViewModel findById(String id) throws Exception {
		Optional<Category> category = this.categoryRepository.findById(Integer.valueOf(id));
		CategoryViewModel categoryViewModel = null;
			if(category.get() != null) {
				 categoryViewModel = this.modelMapper.map(category.get(), CategoryViewModel.class);
			} else {
				throw new Exception("No such category!");
			}
	
		return categoryViewModel;
	}


	@Override
	public void editCategory(int id, String categoryName) {
		Category category = this.categoryRepository.findById(id).get();
		category.setName(categoryName);
		this.categoryRepository.save(category);
	}


	@Override
	public void deleteCategory(int id) {
		this.categoryRepository.deleteById(id);
	}

	
}
