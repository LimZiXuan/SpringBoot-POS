package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CBSEGroup11pos.entity.Cashier;

public interface CashierDao extends JpaRepository<Cashier, Integer>{
	
}
