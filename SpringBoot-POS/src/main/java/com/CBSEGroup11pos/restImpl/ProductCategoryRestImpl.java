package com.CBSEGroup11pos.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.CBSEGroup11pos.rest.ProductCategoryRest;
import com.CBSEGroup11pos.service.ProductCategoryService;

public class ProductCategoryRestImpl implements ProductCategoryRest {

		@Autowired
		private ProductCategoryService productCategoryService;
		
		@Override
		public ResponseEntity<Map<String, Object>> getAllCategory() {

			Map<String, Object> response = productCategoryService.getAllCategory();
			HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(response, status);
		}

		@Override
		public ResponseEntity<Map<String, Object>> getCategory(String categoryId) {

			Map<String, Object> response = productCategoryService.getCategory(categoryId);
			HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(response, status);
		}

		@Override
		public ResponseEntity<Map<String, Object>> addCategory(Map<String, String> requestMap) {

			// Adding the category using the service
			Map<String, Object> response = productCategoryService.addCategory(requestMap);
			HttpStatus status = (boolean) response.get("success") ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(response, status);
		}

		@Override
		public ResponseEntity<Map<String, Object>> updateCategory(String categoryId, Map<String, String> requestMap) {

			Map<String, Object> response = productCategoryService.updateCategory(categoryId, requestMap);
			HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(response, status);
		}

		@Override
		public ResponseEntity<Map<String, Object>> deleteCategory(String categoryId) {

			Map<String, Object> response = productCategoryService.deleteCategory(categoryId);
			HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(response, status);
		}
		
		
}
