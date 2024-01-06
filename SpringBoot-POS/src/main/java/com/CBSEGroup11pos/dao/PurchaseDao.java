package com.CBSEGroup11pos.dao;

import java.util.ArrayList;
import java.util.List;

import com.CBSEGroup11pos.wrapper.PurchaseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Purchase;
import org.springframework.data.repository.query.Param;

public interface PurchaseDao extends JpaRepository<Purchase, Integer> {
	List<Purchase> findByCashierId(Integer cashierId);
	List<Double> getTotalDailySale(@Param("currentDate") String currentDate);
	List<Integer> getTotalDailySaleItemCount(@Param("currentDate") String currentDate);
	int getTotalDailyPurchase(@Param("currentDate") String currentDate);
	List<PurchaseWrapper> getPurchaseBarcodeCategoryName(@Param("currentDate") String currentDate);

}
