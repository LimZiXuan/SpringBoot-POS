package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    int getTotalDailyTransaction(@Param("currentDate") String currentDate);
}
