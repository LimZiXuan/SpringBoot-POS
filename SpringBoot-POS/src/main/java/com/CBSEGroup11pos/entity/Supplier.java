package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String companyName;
	private String lastDateSupplied;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyName;
	}

	public void setCompanyname(String companyname) {
		this.companyName = companyname;
	}

	public String getLastdatesupplied() {
		return lastDateSupplied;
	}

	public void setLastdatesupplied(String lastdatesupplied) {
		this.lastDateSupplied = lastdatesupplied;
	}

}
