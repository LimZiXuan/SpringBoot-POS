package com.CBSEGroup11pos.restImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.CardRest;
import com.CBSEGroup11pos.service.CardService;
import com.CBSEGroup11pos.wrapper.CardWrapper;

@RestController
public class CardRestImpl implements CardRest{
	
	@Autowired
	private CardService cardService;
	
	@Override
	public ResponseEntity<Map<String, Object>> getAllCard() {
		Map<String, Object> response = cardService.findAll();
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
    }

	@Override
	public ResponseEntity<Map<String, Object>> createCard(Map<String, String> requestMap) {
		Map<String, Object> response = cardService.createCard(requestMap);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> updateCard(Map<String, String> requestMap) {
		Map<String, Object> response = cardService.updateCard(requestMap);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
    }
	
	@Override
	public ResponseEntity<Map<String, Object>> deleteCard(String cardNumber) {
		Map<String, Object> response = cardService.deleteCard(cardNumber);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> topUpCard(String cardNumber, String topUpAmount) {
		Map<String, Object> response = cardService.topUpCard(cardNumber, topUpAmount);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
    }
 
}
