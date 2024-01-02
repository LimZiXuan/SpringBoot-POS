package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.dao.SupplierDao;
import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.service.SearchAndSortSupplierService;
import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

@Service
public class SearchAndSortSupplierServiceImpl implements SearchAndSortSupplierService {

	@Autowired
	private ProductItemDao productItemDao;

	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<SearchSupplierWrapper> searchByCompany(String companyName) {
		List<SearchSupplierWrapper> searchResult = new ArrayList<SearchSupplierWrapper>();
		try {
			Supplier supplier = supplierDao.findByCompanyName(companyName).get(0);
			if (supplier != null) {
				List<Object[]> result = supplierDao.findSupplierByCompany(supplier.getId());
				if (!result.isEmpty()) {
					for (Object[] obj : result) {
						SearchSupplierWrapper newObj = new SearchSupplierWrapper();
						newObj.setId((Integer) obj[0]);
						newObj.setCompanyName((String) obj[1]);
						newObj.setProductName((String) obj[2]);
						newObj.setProductCategory((String) obj[3]);
						newObj.setLastDateSupplied((String) obj[4]);
						searchResult.add(newObj);
					}
				}
			}
			return searchResult;
		} catch (Exception e) {
			return searchResult;
		}
	}

	@Override
	public List<SearchSupplierWrapper> searchByProduct(String productName) {
		List<SearchSupplierWrapper> searchResult = new ArrayList<SearchSupplierWrapper>();
		try {
			List<Object[]> result = productItemDao.findSupplierByProduct(productName);
			if (!result.isEmpty()) {
				for (Object[] obj : result) {
					SearchSupplierWrapper newObj = new SearchSupplierWrapper();
					newObj.setId((Integer) obj[0]);
					newObj.setCompanyName((String) obj[1]);
					newObj.setProductName((String) obj[2]);
					newObj.setProductCategory((String) obj[3]);
					newObj.setLastDateSupplied((String) obj[4]);
					searchResult.add(newObj);
				}
			}
			return searchResult;
		} catch (Exception e) {
			return searchResult;
		}
	}

	@Override
	public List<SearchSupplierWrapper> searchByCategory(String productCategory) {
		List<SearchSupplierWrapper> searchResult = new ArrayList<SearchSupplierWrapper>();
		try {
			Integer categoryId = productCategoryDao.findByName(productCategory).get(0).getId();
			if (categoryId != null) {
				List<Object[]> result = productCategoryDao.findSupplierByCategory(categoryId);
				for (Object[] obj : result) {
					SearchSupplierWrapper newObj = new SearchSupplierWrapper();
					newObj.setId((Integer) obj[0]);
					newObj.setCompanyName((String) obj[1]);
					newObj.setProductName((String) obj[2]);
					newObj.setProductCategory((String) obj[3]);
					newObj.setLastDateSupplied((String) obj[4]);
					searchResult.add(newObj);
				}
			}
			return searchResult;
		} catch (Exception e) {
			return searchResult;
		}
	}

	@Override
	public List<SearchSupplierWrapper> sortSupplierByField(String field, String order,
			List<SearchSupplierWrapper> request) {
		if (field.equalsIgnoreCase("product")) {
			if (order.equalsIgnoreCase("ASC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getProductName));
			} else if (order.equalsIgnoreCase("DESC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getProductName).reversed());
			}
		} else if (field.equalsIgnoreCase("category")) {
			if (order.equalsIgnoreCase("ASC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getProductCategory));
			} else if (order.equalsIgnoreCase("DESC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getProductCategory).reversed());
			}
		} else if (field.equalsIgnoreCase("company")) {
			if (order.equalsIgnoreCase("ASC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getCompanyName));
			} else if (order.equalsIgnoreCase("DESC")) {
				Collections.sort(request, Comparator.comparing(SearchSupplierWrapper::getCompanyName).reversed());
			}
		}
		return request;
	}

}
