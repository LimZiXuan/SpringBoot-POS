package com.CBSEGroup11pos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQuery(name = "ProductCategory.findSupplierByCategory", query = "SELECT s.id, s.companyName, p.name, c.name, s.lastDateSupplied "
		+ "FROM ProductItems p INNER JOIN Supplier s ON " + "p.supplierId = s.id INNER JOIN ProductCategory c ON "
		+ "p.categoryId = c.id WHERE c.id = :categoryId")

@Entity
@Table(name = "productcategory")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "datecreated")
	private String dateCreated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatecreated() {
		return dateCreated;
	}

	public void setDatecreated(String datecreated) {
		this.dateCreated = datecreated;
	}

}
