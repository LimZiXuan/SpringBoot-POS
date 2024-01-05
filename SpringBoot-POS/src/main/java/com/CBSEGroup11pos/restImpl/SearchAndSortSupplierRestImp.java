package com.CBSEGroup11pos.restImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.SearchAndSortSupplierRest;
import com.CBSEGroup11pos.service.SearchAndSortSupplierService;
import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

@RestController
public class SearchAndSortSupplierRestImp implements SearchAndSortSupplierRest {

	@Autowired
	private SearchAndSortSupplierService searchSortService;

	@Override
	public ResponseEntity<List<SearchSupplierWrapper>> searchSupplier(String key, String option) {
		if (option.equals("1")) {
			return ResponseEntity.ok(searchSortService.searchByCompany(key));
		} else if (option.equals("2")) {
			return ResponseEntity.ok(searchSortService.searchByProduct(key));
		} else if (option.equals("3")) {
			return ResponseEntity.ok(searchSortService.searchByCategory(key));
		}
		return ResponseEntity.ok(new ArrayList<SearchSupplierWrapper>());
	}

	@Override
	public ResponseEntity<List<SearchSupplierWrapper>> sortSupplier(String field, String order,
			List<SearchSupplierWrapper> request) {
		return ResponseEntity.ok(searchSortService.sortSupplierByField(field, order, request));
	}

}
