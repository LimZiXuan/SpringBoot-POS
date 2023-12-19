package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class CashierServiceImpl implements CashierService{
	
	@Autowired
	CashierDao cashierDao;
	
	@Override
	public Cashier viewCashierInfo(String cashierId) {
//			Business Logic here 
			return cashierDao.findById(cashierId).orElseThrow(() -> new EntityNotFoundException("Cashier not found with id: " + cashierId));
	}
	
	@Override
	public Cashier addCashier(Cashier cashier) {
		return cashierDao.save(cashier);
	}

	@Override
	public ResponseEntity<String> viewSalesHistory(String cashierId) {
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
	        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	    } catch (NumberFormatException e) {
	        // Handle the case where cashierId is not a valid integer
	        return new ResponseEntity<>("Invalid cashierId", HttpStatus.BAD_REQUEST);

	    } catch (Exception e) {
	        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
}
