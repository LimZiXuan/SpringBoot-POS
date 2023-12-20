package com.CBSEGroup11pos.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierService {
	Map<String, Object> getCashier(String cashierId);

	Map<String, Object> addCashier(Map<String, String> requestMap);

	Map<String, Object> updateCashier(String cashierId, Map<String, String> requestMap);

	Map<String, Object> deleteCashier(String cashierId);

	Map<String, Object> viewSalesHistory(String cashierId);

}
