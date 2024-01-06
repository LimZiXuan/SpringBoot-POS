package com.CBSEGroup11pos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    List<Customer> findAll();
}
