package com.CBSEGroup11pos.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RequestMapping(path = "/supplier")
public interface ManageSupplierRest {

	@GetMapping(path = "/get")
	ResponseEntity<List<SupplierWrapper>> getAllSupplier();
}
