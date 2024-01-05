package com.CBSEGroup11pos.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.entity.ProductCategory;
import com.CBSEGroup11pos.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryDao productCategoryDao;

	@Override
	public Map<String, Object> getAllCategory() {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Business Logic here
			List<ProductCategory> entityCards = productCategoryDao.findAll();
			response.put("message", "Categorys is retrieved successfully.");
			response.put("success", true);
			response.put("data", entityCards);
			return response;
		} catch (Exception e) {
			response.put("message", "Categorys has failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> getCategory(String categoryId) {
		Map<String, Object> response = new LinkedHashMap<>();
		int id = Integer.parseInt(categoryId);
		try {
			// Business Logic here
			Optional<ProductCategory> optional = productCategoryDao.findById(id);

			if (optional.isPresent()) {
				ProductCategory fetchedCategory = optional.get();
				response.put("message", "Category with ID : " + categoryId + " has been retrieved successfully.");
				response.put("success", true);
				response.put("data", fetchedCategory);
			} else {
				response.put("message", "Category with ID : " + categoryId + " is not exist.");
				response.put("success", false);
			}

			return response;
		} catch (Exception e) {
			response.put("message", "Category with ID : " + categoryId + " is failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> addCategory(Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Extracting values from the requestMap
			String name = requestMap.get("name");

			// Get the current date and time
			LocalDateTime now = LocalDateTime.now();

			// Format the current date and time to "dd/MM/yyyy" format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dateCreated = now.format(formatter);

			// Creating a Category object
			ProductCategory category = new ProductCategory();
			category.setName(name);
			category.setDatecreated(dateCreated);

			// Adding the category using the service
			ProductCategory newCategory = productCategoryDao.save(category);
			response.put("message", "New category has been created successfully.");
			response.put("success", true);
			response.put("data", newCategory);
			return response;
		} catch (Exception e) {
			response.put("message", "New category creation is failed.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> updateCategory(String categoryId, Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();
		int id = Integer.parseInt(categoryId);
		try {
			Optional<ProductCategory> optional = productCategoryDao.findById(id);

			if (optional.isPresent()) {
				ProductCategory existingCategory = optional.get();

				// Extracting values from the requestMap
				String name = requestMap.get("name");
				String dateCreated = requestMap.get("datecreated");

				// Update properties of the existing Category entity with values from
				// updatedCategory
				existingCategory.setName(name);
				existingCategory.setDatecreated(dateCreated);

				ProductCategory updatedCategory = productCategoryDao.save(existingCategory);

				response.put("message", "Category with ID : " + categoryId + " has been updated successfully.");
				response.put("success", true);
				response.put("data", updatedCategory);
			} else {
				response.put("message", "Category with ID : " + categoryId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			response.put("message", "Category with ID : " + categoryId + " has been failed to update.");
			response.put("success", false);
			return response;
		}
	}
}
