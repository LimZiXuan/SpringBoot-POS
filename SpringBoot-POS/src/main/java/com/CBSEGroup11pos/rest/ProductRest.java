package com.CBSEGroup11pos.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.wrapper.ProductByCategoryWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;

@RequestMapping(path = "/product")
public interface ProductRest {

	@GetMapping
	ResponseEntity<List<ProductItemWrapper>> getAllProduct();
	
	@GetMapping(path = "/{barcode}")
	ResponseEntity<ProductItemWrapper> getProduct(@PathVariable String barcode);

	@PostMapping(path = "/addProduct")
	ResponseEntity<ProductItemWrapper> addProduct(@RequestBody ProductItemWrapper newProduct);

	@PutMapping(path = "/{barcode}")
	ResponseEntity<ProductItemWrapper> updateProduct(@PathVariable String barcode,
			@RequestBody ProductItemWrapper existingProduct);
	
	@PostMapping(path= "/delete/{barcode}")
	ResponseEntity<String> deleteProduct(@PathVariable String barcode);
	
	@GetMapping(path= "/findByCategory/{categoryName}")
	ResponseEntity<List<ProductByCategoryWrapper>> findProductByCategory(@PathVariable String categoryName);
	
	@GetMapping(path = "/{barcode}/QRCode")
	ResponseEntity<byte[]> getProductQRCode(@PathVariable String barcode);
}
