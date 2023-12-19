package com.CBSEGroup11pos.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.entity.Cashier;

@RequestMapping(path = "/cashier")
public interface CashierRest {
	
	// Manage Cashier

	@GetMapping(path = "/{cashierId}")
	ResponseEntity<Cashier> getCashier (@PathVariable String cashierId);
	
	@PostMapping(path = "/addCashier")
	ResponseEntity<String> addCashier(@RequestBody Map<String, String> requestMap);
	
	@PutMapping(path = "/{cashierId}")
	ResponseEntity<Cashier> updateCashier(@PathVariable String cashierId, @RequestBody Map<String, String> requestMap);
	
	@DeleteMapping(path = "/{cashierId}")
	ResponseEntity<String> deleteCashier(@PathVariable String cashierId);
	
	// View Sales History
	
	@GetMapping(path = "/{cashierId}/sales-history")
	ResponseEntity<String> viewSalesHistory (@PathVariable String cashierId);

}
