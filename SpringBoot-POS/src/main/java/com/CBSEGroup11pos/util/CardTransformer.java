package com.CBSEGroup11pos.util;

import org.springframework.stereotype.Component;

import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.wrapper.CardWrapper;

@Component
public class CardTransformer {

	public CardWrapper transformEntityToObj(Card entity) {
		CardWrapper obj = new CardWrapper();
		obj.setCardNumber(entity.getCardNumber());
		obj.setCustomerId(entity.getCustomerId());
		obj.setAmount(entity.getAmount());
		obj.setLastUsedDate(entity.getLastUsedDate());
		obj.setRegisterDate(entity.getRegisterDate());
		obj.setExpiredDate(entity.getExpiredDate());
		obj.setPin(entity.getPin());
		return obj;
	}

	public Card transformObjToEntity(CardWrapper obj) {
		Card entity = new Card();
		entity.setCardNumber(obj.getCardNumber());
		entity.setCustomerId(obj.getCustomerId());
		entity.setAmount(obj.getAmount());
		entity.setLastUsedDate(obj.getLastUsedDate());
		entity.setRegisterDate(obj.getRegisterDate());
		entity.setExpiredDate(obj.getExpiredDate());
		entity.setPin(obj.getPin());
		return entity;
	}
	
}