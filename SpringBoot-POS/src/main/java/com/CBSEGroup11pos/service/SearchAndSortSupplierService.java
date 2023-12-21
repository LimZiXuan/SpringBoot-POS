package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

public interface SearchAndSortSupplierService {

	List<SearchSupplierWrapper> searchByCompany(String companyName);

	List<SearchSupplierWrapper> searchByProduct(String productName);

	List<SearchSupplierWrapper> searchByCategory(String productCategory);

	List<SearchSupplierWrapper> sortSupplierByField(String field, String order, List<SearchSupplierWrapper> request);
}
