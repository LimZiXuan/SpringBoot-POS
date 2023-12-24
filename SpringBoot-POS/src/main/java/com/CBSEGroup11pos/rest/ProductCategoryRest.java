package com.CBSEGroup11pos.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/product/category")
public interface ProductCategoryRest {
	@GetMapping
	ResponseEntity<Map<String, Object>> getAllCategory();

	@GetMapping(path = "/{categoryId}")
	ResponseEntity<Map<String, Object>> getCategory(@PathVariable String categoryId);

	@PostMapping(path = "/addCategory")
	ResponseEntity<Map<String, Object>> addCategory(@RequestBody Map<String, String> requestMap);

	@PutMapping(path = "/{categoryId}")
	ResponseEntity<Map<String, Object>> updateCategory(@PathVariable String categoryId,
			@RequestBody Map<String, String> requestMap);
}
