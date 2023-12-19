package com.CBSEGroup11pos.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/cashier")
public interface CashierRest {

	@GetMapping(path = "/{cashierId}/sales-history")
	ResponseEntity<String> viewSalesHistory (@PathVariable Long cashierId);
	

}
