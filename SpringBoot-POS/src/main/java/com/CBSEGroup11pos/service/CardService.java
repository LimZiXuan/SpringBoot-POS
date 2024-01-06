package com.CBSEGroup11pos.service;

import java.util.Map;

public interface CardService {
	Map<String, Object> findAll();
	Map<String, Object> createCard(Map<String, String> requestMap);
	Map<String, Object> updateCard(Map<String, String> requestMap);
	Map<String, Object> deleteCard(String cardNumber);
	Map<String, Object> topUpCard(String cardNumber, String topUpAmount);
}
