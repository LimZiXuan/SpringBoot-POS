package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Productitems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")

	private String name;
	private Integer categoryid;
	private String dateadded;
	private String expireddate;
	private String price;
	private String barcode;
	private Integer supplierid;
	private String stockamount;
	private Integer count;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getDateadded() {
		return dateadded;
	}
	public void setDateadded(String dateadded) {
		this.dateadded = dateadded;
	}
	public String getExpireddate() {
		return expireddate;
	}
	public void setExpireddate(String expireddate) {
		this.expireddate = expireddate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}
	public String getStockamount() {
		return stockamount;
	}
	public void setStockamount(String stockamount) {
		this.stockamount = stockamount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
