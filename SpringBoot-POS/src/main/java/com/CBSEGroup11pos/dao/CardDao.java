package com.CBSEGroup11pos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CBSEGroup11pos.entity.Card;

public interface CardDao extends JpaRepository<Card, String> {
}
