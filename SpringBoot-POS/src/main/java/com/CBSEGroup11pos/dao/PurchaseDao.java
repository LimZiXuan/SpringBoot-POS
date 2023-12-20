package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Purchase;

public interface PurchaseDao extends JpaRepository<Purchase, Integer> {
}
