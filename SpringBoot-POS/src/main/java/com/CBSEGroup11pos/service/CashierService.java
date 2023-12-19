package com.CBSEGroup11pos.service;

import org.springframework.http.ResponseEntity;

public interface CashierService {
	ResponseEntity<String> viewSalesHistory (Long cashierId);
}
