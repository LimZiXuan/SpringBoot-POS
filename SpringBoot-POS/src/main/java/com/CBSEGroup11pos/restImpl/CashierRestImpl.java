package com.CBSEGroup11pos.restImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.entity.Cashier;
import com.CBSEGroup11pos.rest.CashierRest;
import com.CBSEGroup11pos.service.CashierService;

@RestController
public class CashierRestImpl implements CashierRest {

	@Autowired
	private CashierService cashierService;
	
	@Override
	public ResponseEntity<Map<String, Object>> getAllCashier() {

		Map<String, Object> response = cashierService.getAllCashier();
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCashier(String cashierId) {

		Map<String, Object> response = cashierService.getCashier(cashierId);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> addCashier(Map<String, String> requestMap) {

		// Adding the cashier using the service
		Map<String, Object> response = cashierService.addCashier(requestMap);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateCashier(String cashierId, Map<String, String> requestMap) {

		Map<String, Object> response = cashierService.updateCashier(cashierId, requestMap);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteCashier(String cashierId) {
		
		Map<String, Object> response = cashierService.deleteCashier(cashierId);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> viewSalesHistory(String cashierId) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			cashierService.viewSalesHistory(cashierId);
			response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " deletion is failed.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
