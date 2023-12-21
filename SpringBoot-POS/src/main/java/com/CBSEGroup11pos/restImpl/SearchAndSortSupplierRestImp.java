package com.CBSEGroup11pos.restImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.SearchAndSortSupplierRest;
import com.CBSEGroup11pos.service.SearchAndSortSupplierService;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RestController
public class SearchAndSortSupplierRestImp implements SearchAndSortSupplierRest {

	@Autowired
	private SearchAndSortSupplierService searchSortService;

	@Override
	public ResponseEntity<List<SupplierWrapper>> searchSupplierFromName(String key, String option) {
		searchSortService.searchByCompany(1);
		return null;
	}

}
