package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

public interface SearchAndSortSupplierService {

	List<Object[]> searchByCompany(Integer company);
}
