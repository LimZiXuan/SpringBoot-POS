package com.CBSEGroup11pos.restImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.ProductRest;
import com.CBSEGroup11pos.service.ProductService;
import com.CBSEGroup11pos.wrapper.ProductByCategoryWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;

@RestController
public class ProductRestImpl implements ProductRest {

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<List<ProductItemWrapper>> getAllProduct() {
		return ResponseEntity.ok(productService.getAllProduct());
	}
	
	@Override
	public ResponseEntity<ProductItemWrapper> getProduct(String barcode) {
		return ResponseEntity.ok(productService.getProduct(barcode));
	}
	
	@Override
	public ResponseEntity<ProductItemWrapper> addProduct(ProductItemWrapper newProduct) {
		return ResponseEntity.ok(productService.addProduct(newProduct));
	}
	
	@Override
	public ResponseEntity<ProductItemWrapper> updateProduct(String barcode, ProductItemWrapper existingProduct) {
		return ResponseEntity.ok(productService.updateProduct(barcode, existingProduct));
	}
	
	@Override
	public ResponseEntity<String> deleteProduct(String barcode) {
		return ResponseEntity.ok(productService.deleteProduct(barcode));
	}
	
	@Override
	public ResponseEntity<List<ProductByCategoryWrapper>> findProductByCategory(String categoryName){
		return ResponseEntity.ok(productService.findProductByCategory(categoryName));
	}
	

}
