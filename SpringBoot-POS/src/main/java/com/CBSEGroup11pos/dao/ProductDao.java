package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{
//	Product findById();
}
