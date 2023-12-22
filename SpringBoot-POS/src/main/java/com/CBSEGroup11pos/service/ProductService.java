package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.wrapper.ProductByCategoryWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;

public interface ProductService {
	List<ProductItemWrapper> getAllProduct();

	ProductItemWrapper getProduct(String barcode);

	ProductItemWrapper addProduct(ProductItemWrapper newProduct);

	ProductItemWrapper updateProduct(String barcode, ProductItemWrapper existingProduct);
	
	String deleteProduct(String barcode);
	
	List<ProductByCategoryWrapper> findProductByCategory(String categoryName);
}
