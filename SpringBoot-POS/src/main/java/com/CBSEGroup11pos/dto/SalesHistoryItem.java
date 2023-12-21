package com.CBSEGroup11pos.dto;

public class SalesHistoryItem {
	private String barcode;
	private String name;
	private String date;
	private String quantity;
	private String totalAmount;

	public SalesHistoryItem(String barcode, String name, String date, String quantity, String totalAmount) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.date = date;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
