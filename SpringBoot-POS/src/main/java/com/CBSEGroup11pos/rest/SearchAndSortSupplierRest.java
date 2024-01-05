package com.CBSEGroup11pos.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

@RequestMapping(path = "/supplier/search")
public interface SearchAndSortSupplierRest {

	@GetMapping
	ResponseEntity<List<SearchSupplierWrapper>> searchSupplier(@RequestParam(required = true) String key,
			@RequestParam(required = true) String option);

	@PostMapping(path = "/sort")
	ResponseEntity<List<SearchSupplierWrapper>> sortSupplier(@RequestParam(required = true) String field,
			@RequestParam(required = true) String order, @RequestBody List<SearchSupplierWrapper> request);
}
