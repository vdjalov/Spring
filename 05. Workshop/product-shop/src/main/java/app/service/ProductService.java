package app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import app.data.models.Product;
import app.service.models.AddProductServiceModel;
import app.service.models.EditProductServiceModel;
import app.web.models.FindAllProductsView;
import app.web.models.ProductDetailsView;

public interface ProductService {

	void saveProduct(AddProductServiceModel addProductServiceModel) throws IOException;

	List<FindAllProductsView> getAllProducts();

	ProductDetailsView getProductById(int id);

	void amendProduct(EditProductServiceModel editProductServiceModel, int id);

	void deleteProductById(int id);
	Optional<Product> findProductById(int productId);
}
