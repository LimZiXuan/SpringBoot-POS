package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.ProductItems;

public interface ProductItemsDao extends JpaRepository<ProductItems, String> {

//	@Query("SELECT s.id,s.companyname,p.name,p2,name,s.lastdatesupplied "
//			+ "FROM productitems p INNER JOIN supplier s ON " + "p.supplierid = s.id INNER JOIN productcategory p2 ON "
//			+ "p.categoryid = p2.id WHERE p.supplierid = :supplierid")
//	List<Object[]> getSupplierWithDetails(@Param("supplierid") Integer supplierid);
}
