package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer>{
	Product findById();
}
