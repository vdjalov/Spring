package app.service;

import java.util.List;

import app.service.models.GetCategoryServiceModel;
import app.web.models.CategoryViewModel;

public interface CategoryService {
	
		void addCategory(GetCategoryServiceModel getCategoryServiceModel);

		List<CategoryViewModel> findAllCategories();

		CategoryViewModel findById(String id) throws Exception;

		void editCategory(int id, String categoryName);

		void deleteCategory(int id);
}
