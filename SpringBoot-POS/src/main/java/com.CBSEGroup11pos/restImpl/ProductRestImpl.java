package com.CBSEGroup11pos.restImpl;

import java.util.Map;

import com.CBSEGroup11pos.rest.ProductRest;
import com.CBSEGroup11pos.service.ProductService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
public class ProductRestImpl implements ProductRest {
	
	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
		try {
			return productService.addProduct(requestMap);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
