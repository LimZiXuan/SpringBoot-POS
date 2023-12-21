package com.CBSEGroup11pos.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RequestMapping(path = "/search")
public interface SearchAndSortSupplierRest {

	@GetMapping
	ResponseEntity<List<SupplierWrapper>> searchSupplierFromName(@RequestParam(required = true) String key,
			String option);
}
