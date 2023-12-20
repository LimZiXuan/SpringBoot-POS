package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CashierDao;
import com.CBSEGroup11pos.entity.Cashier;
import com.CBSEGroup11pos.service.CashierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CashierServiceImpl implements CashierService {

	@Autowired
	CashierDao cashierDao;

	@Override
	public Map<String, Object> getAllCashier() {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Business Logic here
			List<Cashier> entityCards = cashierDao.findAll();
			response.put("message", "Cashiers is retrieved successfully.");
			response.put("success", true);
			response.put("data", entityCards);
			return response;
		} catch (Exception e) {
			response.put("message", "Cashiers has failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> getCashier(String cashierId) {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Business Logic here
			Optional<Cashier> optional = cashierDao.findById(cashierId);

			if (optional.isPresent()) {
				Cashier fetchedCashier = optional.get();
				response.put("message", "Cashier with ID : " + cashierId + " has been retrieved successfully.");
				response.put("success", true);
				response.put("data", fetchedCashier);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}

			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " is failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> addCashier(Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Extracting values from the requestMap
			String name = requestMap.get("name");
			Integer age = Integer.parseInt(requestMap.get("age"));
			String gender = requestMap.get("gender");
			String address = requestMap.get("address");
			String phone = requestMap.get("phone");
			String email = requestMap.get("email");
			String password = requestMap.get("password");
			String dateCreated = requestMap.get("datecreated");

			// Creating a Cashier object
			Cashier cashier = new Cashier();
			cashier.setName(name);
			cashier.setAge(age);
			cashier.setGender(gender);
			cashier.setAddress(address);
			cashier.setPhone(phone);
			cashier.setEmail(email);
			cashier.setPassword(password);
			cashier.setDatecreated(dateCreated);

			// Adding the cashier using the service
			Cashier newCashier = cashierDao.save(cashier);
			response.put("message", "New cashier has been created successfully.");
			response.put("success", true);
			response.put("data", newCashier);
			return response;
		} catch (Exception e) {
			response.put("message", "New cashier creation is failed.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> updateCashier(String cashierId, Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			Optional<Cashier> optional = cashierDao.findById(cashierId);

			if (optional.isPresent()) {
				Cashier existingCashier = optional.get();

				// Extracting values from the requestMap
				String name = requestMap.get("name");
				Integer age = Integer.parseInt(requestMap.get("age"));
				String gender = requestMap.get("gender");
				String address = requestMap.get("address");
				String phone = requestMap.get("phone");
				String email = requestMap.get("email");
				String password = requestMap.get("password");
				String dateCreated = requestMap.get("datecreated");

				// Update properties of the existing Cashier entity with values from
				// updatedCashier
				existingCashier.setName(name);
				existingCashier.setAge(age);
				existingCashier.setGender(gender);
				existingCashier.setAddress(address);
				existingCashier.setPhone(phone);
				existingCashier.setEmail(email);
				existingCashier.setPassword(password);
				existingCashier.setDatecreated(dateCreated);

				Cashier updatedCashier = cashierDao.save(existingCashier);

				response.put("message", "Cashier with ID : " + cashierId + " has been updated successfully.");
				response.put("success", true);
				response.put("data", updatedCashier);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " has been failed to update.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> deleteCashier(String cashierId) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			if (cashierDao.existsById(cashierId)) {
				cashierDao.deleteById(cashierId);
				response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
				response.put("success", true);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " deletion is failed.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> viewSalesHistory(String cashierId) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Fetch purchase details
			List<Object[]> purchaseDetails = cashierDao.getCashierPurchaseDetails(cashierId);

			// Log the purchase details to console for debugging
			System.out.println("Purchase Details:");
			for (Object[] row : purchaseDetails) {
				if (row != null) {
					System.out.println(Arrays.toString(row));
				} else {
					System.out.println("It's null");
				}
			}

			// Process and construct JSON response
			List<ObjectNode> jsonList = new ArrayList<>();
			ObjectMapper objectMapper = new ObjectMapper();

			for (Object[] row : purchaseDetails) {
				ObjectNode json = objectMapper.createObjectNode();
				json.put("barcode", (String) row[0]);
				json.put("productName", (String) row[1]);
				json.put("purchaseDate", (String) row[2]);
				json.put("quantity", (String) row[3]);
				json.put("totalAmount", (String) row[4]);
				jsonList.add(json);
			}

			// Convert the list of JSON objects to a JSON array
			ArrayNode jsonArray = objectMapper.valueToTree(jsonList);

			// Construct the final response string
			String jsonResponse = jsonArray.toString();
			response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
			response.put("success", false);
			return response;
		}
	}
}
