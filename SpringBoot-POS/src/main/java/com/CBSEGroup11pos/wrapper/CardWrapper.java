package com.CBSEGroup11pos.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardWrapper {
	
	private String cardNumber;
	private String customerId;
	private String amount;
	private String lastUsedDate;
	private String registerDate;
	private String expiredDate;
	private Integer pin;
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getLastUsedDate() {
		return lastUsedDate;
	}
	
	public void setLastUsedDate(String lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}
	
	public String getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	public String getExpiredDate() {
		return expiredDate;
	}
	
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	public Integer getPin() {
		return pin;
	}
	
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
}
