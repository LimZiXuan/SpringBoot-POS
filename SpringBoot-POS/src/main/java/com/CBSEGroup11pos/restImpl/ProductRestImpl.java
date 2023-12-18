package com.CBSEGroup11pos.restImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.ProductRest;
import com.CBSEGroup11pos.service.ProductService;

@RestController
public class ProductRestImpl implements ProductRest{
	
	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<String> addProduct(String requestMap) {
		try {
			return productService.addProduct(requestMap);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
