package com.CBSEGroup11pos.dao;

import java.util.ArrayList;
import java.util.List;

import com.CBSEGroup11pos.wrapper.ProductInfoWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

	List<ProductCategory> findByName(String name);
	List<Object[]> findSupplierByCategory(@Param("categoryId") Integer categoryId);
	List<String> getCategoryNameList();
	List<ProductInfoWrapper> getProductCategoryPrice(@Param("barcode")String barcode);
}
