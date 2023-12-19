package com.CBSEGroup11pos.service;

import org.springframework.http.ResponseEntity;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierService {
	Cashier viewCashierInfo(String cashierId);
	Cashier addCashier(Cashier cashier);
	ResponseEntity<String> viewSalesHistory (String cashierId);
	
}
