package com.CBSEGroup11pos.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RequestMapping(path = "/supplier")
public interface ManageSupplierRest {

	@GetMapping(path = "/get")
	ResponseEntity<List<SupplierWrapper>> getAllSupplier();

	@PutMapping(path = "/add")
	ResponseEntity<SupplierWrapper> addSupplier(@RequestBody(required = true) SupplierWrapper newSupplier);

	@PostMapping(path = "/update")
	ResponseEntity<SupplierWrapper> updateSupplier(@RequestBody(required = true) SupplierWrapper existingSupplier);

	@PostMapping(path = "/delete")
	ResponseEntity<String> deleteSupplier(@RequestBody(required = true) SupplierWrapper existingSupplier);
}
