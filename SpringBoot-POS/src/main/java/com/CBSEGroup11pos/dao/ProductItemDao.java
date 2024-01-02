package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.ProductItems;

public interface ProductItemDao extends JpaRepository<ProductItems, String> {
	ProductItems findByBarcode(String barcode);

	List<Object[]> findSupplierByProduct(@Param("name") String name);
}
