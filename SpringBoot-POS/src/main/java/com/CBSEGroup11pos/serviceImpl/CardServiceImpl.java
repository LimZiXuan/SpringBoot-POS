package com.CBSEGroup11pos.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CardDao;
import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.service.CardService;
import com.CBSEGroup11pos.util.CardTransformer;
import com.CBSEGroup11pos.wrapper.CardWrapper;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardDao cardDao;

	@Autowired
	private CardTransformer cardTransformer;

	@Override
	public Map<String, Object> findAll() {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
    		List<CardWrapper> allCards = new ArrayList<>();
    		List<Card> entityCards = cardDao.findAll();
    		for (Card data : entityCards) {
    			allCards.add(cardTransformer.transformEntityToObj(data));
    		}
            response.put("message", "Cards Retrieved Successfully");
            response.put("success", true);
            response.put("data", allCards);
            return response;
        } catch (Exception e) {
            response.put("message", "Retrieving Cards Failed");
            response.put("success", false);
            return response;
        }
	}
	
	@Override
	public Map<String, Object> createCard(Map<String, String> requestMap) {
        Map<String, Object> response = new LinkedHashMap<>();
		try {
			
			if (cardDao.existsById(requestMap.get("cardNumber"))) {
                response.put("message", "Card Already Existed!");
                response.put("success", false);
                return response;
            }
			
	        Card card = new Card();
	        card.setCardNumber(requestMap.get("cardNumber"));
	        card.setCustomerId(requestMap.get("customerId"));
	        card.setAmount(requestMap.get("amount"));
	        card.setLastUsedDate(requestMap.get("lastUsedDate"));
	        card.setRegisterDate(requestMap.get("registerDate"));
	        card.setExpiredDate(requestMap.get("expiredDate"));
	        card.setPin(Integer.parseInt(requestMap.get("pin")));
        
	        Card createdCard = cardDao.save(card);
	        response.put("message", "Card Created Successfully");
            response.put("success", true);
            response.put("data", cardTransformer.transformEntityToObj(createdCard));
            return response;
	    } catch (Exception e) {
	        response.put("message", "Creating Card Failed");
            response.put("success", false);
            return response;
	    }
	}
	
	@Override
	public Map<String, Object> updateCard(Map<String, String> requestMap) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
	        Card existingCard = cardDao.findById(requestMap.get("cardNumber")).orElse(null);
        	existingCard.setCustomerId(requestMap.get("customerId"));
            existingCard.setAmount(requestMap.get("amount"));
            existingCard.setLastUsedDate(requestMap.get("lastUsedDate"));
            existingCard.setRegisterDate(requestMap.get("registerDate"));
            existingCard.setExpiredDate(requestMap.get("expiredDate"));
            existingCard.setPin(Integer.parseInt(requestMap.get("pin")));
            Card updatedCard = cardDao.save(existingCard);
            
            response.put("message", "Card Updated Successfully");
            response.put("success", true);
            response.put("data", cardTransformer.transformEntityToObj(updatedCard));
            return response;
        } catch (Exception e) {
            response.put("message", "Updating Card Failed");
            response.put("success", false);
            return response;
        }	 
	}
	
	@Override
	public Map<String, Object> deleteCard(String cardNumber) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
        	
			if (!cardDao.existsById(cardNumber)) {
                response.put("message", "Card Does Not Existed!");
                response.put("success", false);
                return response;
            }
			
        	cardDao.deleteById(cardNumber);
            response.put("message", "Card Deleted Successfully");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Deleting Card Failed");
            response.put("success", false);
            return response;
        }
	}
	
	@Override
	public Map<String, Object> topUpCard(String cardNumber, String topUpAmount) {
        Map<String, Object> response = new LinkedHashMap<>();
		try {	
	        Card existingCard = cardDao.findById(cardNumber).orElse(null);
	        
            BigDecimal oldAmount = new BigDecimal(existingCard.getAmount());
            BigDecimal topUpAmountNumber = new BigDecimal(topUpAmount);
            
            if (topUpAmountNumber.compareTo(BigDecimal.ZERO) < 0) {
                topUpAmountNumber = BigDecimal.ZERO;
            }
            
            BigDecimal newAmount = oldAmount.add(topUpAmountNumber);
	        
            existingCard.setAmount(newAmount.toString());
            Card updatedCard = cardDao.save(existingCard);
			
	        response.put("message", "Top Up Successful");
            response.put("success", true);
            response.put("Old Amount", oldAmount.toString());
            response.put("Top Up Amount", topUpAmountNumber.toString());
            response.put("New Amount", updatedCard.getAmount());
            response.put("data", cardTransformer.transformEntityToObj(updatedCard));
            return response;
	    } catch (Exception e) {
	        response.put("message", "Top Up Failed");
            response.put("success", false);
            return response;
	    }
	}
	
}
