package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardnumber")
	private String cardNumber;

	@Column(name = "customerid")
	private String customerId;

	@Column(name = "amount")
	private String amount;

	@Column(name = "lastuseddate")
	private String lastUsedDate;

	@Column(name = "registerdate")
	private String registerDate;

	@Column(name = "expireddate")
	private String expiredDate;

	@Column(name = "pin")
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
