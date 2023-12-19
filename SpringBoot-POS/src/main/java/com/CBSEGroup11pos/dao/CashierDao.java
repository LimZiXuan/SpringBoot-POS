package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierDao extends JpaRepository<Cashier, String>{
	@Query("SELECT pu.barcode, pi.name, pu.date, pu.quantity, pu.totalAmount " +
	           "FROM Purchase pu " +
	           "JOIN ProductItems pi ON pu.barcode = pi.barcode " +
	           "WHERE pu.cashierId = :cashierId")
	    List<Object[]> getCashierPurchaseDetails(@Param("cashierId") String cashierId);
}
