package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.wrapper.SearchSupplierWrapper;

public interface ProductItemsDao extends JpaRepository<ProductItems, String> {

	List<Object[]> findSupplierByProduct(@Param("name") String name);
}
