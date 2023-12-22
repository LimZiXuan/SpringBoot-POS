package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.ProductItems;

public interface ProductItemDao extends JpaRepository<ProductItems, String> {
	ProductItems findByBarcode(String barcode);
}
