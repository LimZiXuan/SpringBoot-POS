package com.CBSEGroup11pos.service;

import org.springframework.http.ResponseEntity;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierService {
	Cashier getCashier(String cashierId);
	Cashier addCashier(Cashier cashier);
	Cashier updateCashier(String cashierId, Cashier updatedCashier);
	void deleteCashier(String cashierId);
	ResponseEntity<String> viewSalesHistory (String cashierId);
	
}
