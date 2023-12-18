package com.CBSEGroup11pos.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ProductService {
	ResponseEntity<String> addProduct(String requestMap);
}
