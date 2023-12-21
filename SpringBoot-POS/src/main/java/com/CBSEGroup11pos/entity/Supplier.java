package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQuery(name = "Supplier.findSupplierByCompany", query = "SELECT s.id, s.companyName, p.name, c.name, s.lastDateSupplied "
		+ "FROM ProductItems p INNER JOIN Supplier s ON " + "p.supplierId = s.id INNER JOIN ProductCategory c ON "
		+ "p.categoryId = c.id WHERE p.supplierId = :supplierid")

@Entity
@Table(name = "supplier")
public class Supplier {
	@Id
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
