package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {

	List<Supplier> findByCompanyName(String companyName);

	List<Object[]> findSupplierByCompany(@Param("supplierid") Integer supplierid);

}
