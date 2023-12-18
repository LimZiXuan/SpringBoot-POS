package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;

	@Column(name = "cashierid")
	private String cashierId;

	@Column(name = "cardid")
	private String cardId;

	@Column(name = "purchaseid")
	private String purchaseId;

	@Column(name = "amount")
	private String amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCashierid() {
		return cashierId;
	}

	public void setCashierid(String cashierid) {
		this.cashierId = cashierid;
	}

	public String getCardid() {
		return cardId;
	}

	public void setCardid(String cardid) {
		this.cardId = cardid;
	}

	public String getPurchaseid() {
		return purchaseId;
	}

	public void setPurchaseid(String purchaseid) {
		this.purchaseId = purchaseid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
