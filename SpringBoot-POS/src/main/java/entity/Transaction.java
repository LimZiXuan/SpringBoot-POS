package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	private String cashierid;
	private String cardid;
	private String purchaseid;
	private String amount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCashierid() {
		return cashierid;
	}
	public void setCashierid(String cashierid) {
		this.cashierid = cashierid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
