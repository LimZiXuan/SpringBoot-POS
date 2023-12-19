package com.CBSEGroup11pos.service;

import org.springframework.http.ResponseEntity;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierService {
	ResponseEntity<Cashier> viewCashierInfo(String cashierId);
	ResponseEntity<String> viewSalesHistory (String cashierId);
	
}
