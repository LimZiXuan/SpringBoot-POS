package com.CBSEGroup11pos.service;

import java.util.Map;

public interface ProductCategoryService {
	Map<String, Object> getAllCategory();

	Map<String, Object> getCategory(String categoryId);

	Map<String, Object> addCategory(Map<String, String> requestMap);

	Map<String, Object> updateCategory(String categoryId, Map<String, String> requestMap);
}
