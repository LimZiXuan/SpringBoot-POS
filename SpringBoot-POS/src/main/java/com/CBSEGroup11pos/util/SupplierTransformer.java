package com.CBSEGroup11pos.util;

import org.springframework.stereotype.Component;

import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@Component
public class SupplierTransformer {

	public SupplierWrapper transformEntityToObj(Supplier entity) {
		SupplierWrapper obj = new SupplierWrapper();
		obj.setId(entity.getId());
		obj.setCompanyName(entity.getCompanyName());
		obj.setLastdateSupplied(entity.getLastDateSupplied());
		return obj;
	}

	public Supplier transformObjToEntity(SupplierWrapper obj) {
		Supplier entity = new Supplier();
		entity.setId(obj.getId());
		entity.setCompanyName(obj.getCompanyName());
		entity.setLastDateSupplied(obj.getLastdateSupplied());
		return entity;
	}
}
