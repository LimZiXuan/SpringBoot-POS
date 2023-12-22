package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
}