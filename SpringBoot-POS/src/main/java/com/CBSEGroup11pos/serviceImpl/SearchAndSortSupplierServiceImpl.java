package com.CBSEGroup11pos.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductItemsDao;
import com.CBSEGroup11pos.service.SearchAndSortSupplierService;

@Service
public class SearchAndSortSupplierServiceImpl implements SearchAndSortSupplierService {

	@Autowired
	private ProductItemsDao productItemDao;

	@Override
	public List<Object[]> searchByCompany(Integer company) {
		productItemDao.count();
		return null;
	}

}
