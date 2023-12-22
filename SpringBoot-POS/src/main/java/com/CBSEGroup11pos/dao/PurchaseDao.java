package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Purchase;

public interface PurchaseDao extends JpaRepository<Purchase, String> {
	List<Purchase> findByCashierId(Integer cashierId);
}
