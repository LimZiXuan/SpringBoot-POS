package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "cashierid")
	private Integer cashierId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "totalamount")
	private String totalAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getCashierid() {
		return cashierId;
	}

	public void setCashierid(Integer cashierid) {
		this.cashierId = cashierid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalamount() {
		return totalAmount;
	}

	public void setTotalamount(String totalamount) {
		this.totalAmount = totalamount;
	}

}
