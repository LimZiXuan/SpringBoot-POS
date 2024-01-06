package com.CBSEGroup11pos.entity;

import jakarta.persistence.*;
import lombok.Data;

@NamedQuery(name = "Transaction.getTotalDailyTransaction", query = "SELECT COUNT(*) FROM Transaction t JOIN Purchase p ON t.purchaseId = p.id WHERE p.date=:currentDate")

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "cashierid")
	private String cashierId;

	@Column(name = "cardid")
	private String cardId;

	@Column(name = "purchaseid")
	private String purchaseId;

	@Column(name = "amount")
	private String amount;

}
