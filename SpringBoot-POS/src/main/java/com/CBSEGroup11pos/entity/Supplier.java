package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "lastdatesupplied")
	private String lastDateSupplied;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLastDateSupplied() {
		return lastDateSupplied;
	}

	public void setLastDateSupplied(String lastDateSupplied) {
		this.lastDateSupplied = lastDateSupplied;
	}

}
