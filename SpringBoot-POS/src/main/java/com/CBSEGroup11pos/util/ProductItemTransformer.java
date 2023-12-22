package com.CBSEGroup11pos.util;

import org.springframework.stereotype.Component;

import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;

@Component
public class ProductItemTransformer {
	
	public ProductItemWrapper transformEntityToObj(ProductItems entity) {
		ProductItemWrapper obj = new ProductItemWrapper();
		obj.setBarcode(entity.getBarcode());
		obj.setCategoryId(entity.getCategoryId());
		obj.setCount(entity.getCount());
		obj.setDateAdded(entity.getDateAdded());
		obj.setExpiredDate(entity.getExpiredDate());
		obj.setName(entity.getName());
		obj.setPrice(entity.getPrice());
		obj.setStockAmount(entity.getStockAmount());
		obj.setSupplierId(entity.getSupplierId());
		return obj;
	}
	
	public ProductItems transformObjToEntity(ProductItemWrapper obj) {
		ProductItems entity = new ProductItems();
		entity.setBarcode(obj.getBarcode());
		entity.setCategoryId(obj.getCategoryId());
		entity.setCount(obj.getCount());
		entity.setDateAdded(obj.getDateAdded());
		entity.setExpiredDate(obj.getExpiredDate());
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());
		entity.setStockAmount(obj.getStockAmount());
		entity.setSupplierId(obj.getSupplierId());
		return entity;
	}
}
