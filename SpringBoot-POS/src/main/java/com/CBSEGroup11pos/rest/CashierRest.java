package com.CBSEGroup11pos.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.entity.Cashier;

@RequestMapping(path = "/cashier")
public interface CashierRest {

	@GetMapping(path = "/{cashierId}")
	ResponseEntity<Cashier> viewCashierInfo (@PathVariable String cashierId);
	
	@GetMapping(path = "/{cashierId}/sales-history")
	ResponseEntity<String> viewSalesHistory (@PathVariable String cashierId);
	

}
