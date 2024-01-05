package com.CBSEGroup11pos.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/purchase-invoice")
public interface PurchaseInvoiceRest {
	
	@GetMapping(path = "/generete-invoice/{purchaseId}")
	ResponseEntity<Map<String, Object>> genereteInvoice(@PathVariable Integer purchaseId);

	@GetMapping(path = "/print-invoice/{purchaseId}")
	ResponseEntity<byte[]> printInvoice(@PathVariable Integer purchaseId);
	
	@GetMapping(path = "/email-invoice/{purchaseId}")
	ResponseEntity<Map<String, Object>> emailInvoice(@PathVariable Integer purchaseId);
}
