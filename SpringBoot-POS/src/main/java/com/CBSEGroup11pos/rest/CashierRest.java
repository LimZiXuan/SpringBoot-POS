package com.CBSEGroup11pos.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.entity.Cashier;

@RequestMapping(path = "/cashier")
public interface CashierRest {

	@GetMapping(path = "/{cashierId}")
	ResponseEntity<Cashier> viewCashierInfo (@PathVariable String cashierId);
	
	@PostMapping(path = "/addCashier")
	ResponseEntity<Cashier> addCashier(@RequestBody Map<String, String> requestMap);
	
	@GetMapping(path = "/{cashierId}/sales-history")
	ResponseEntity<String> viewSalesHistory (@PathVariable String cashierId);
	

}
