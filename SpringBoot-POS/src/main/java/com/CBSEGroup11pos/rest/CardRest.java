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
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/card")
public interface CardRest {
	
	@GetMapping(path = "/get")
	ResponseEntity<Map<String, Object>> getAllCard();
	
	@PostMapping(path = "/create")
	ResponseEntity<Map<String, Object>> createCard(@RequestBody Map<String, String> requestMap);

	@PutMapping(path = "/update")
	ResponseEntity<Map<String, Object>> updateCard(@RequestBody Map<String, String> requestMap);

	@DeleteMapping(path = "/delete/{cardNumber}")
	ResponseEntity<Map<String, Object>> deleteCard(@PathVariable String cardNumber);

	@PutMapping(path = "/top-up")
	ResponseEntity<Map<String, Object>> topUpCard(
	    @RequestParam String cardNumber,
	    @RequestParam String topUpAmount
	);

}
