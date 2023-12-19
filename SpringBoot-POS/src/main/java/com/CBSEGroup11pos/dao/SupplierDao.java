package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {
}
