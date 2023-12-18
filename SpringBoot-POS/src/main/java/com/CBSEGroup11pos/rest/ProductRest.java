package com.CBSEGroup11pos.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/product")
public interface ProductRest {

	
	@PostMapping(path = "/addProduct")
	ResponseEntity<String> addProduct(@RequestBody String requestMap);
}
