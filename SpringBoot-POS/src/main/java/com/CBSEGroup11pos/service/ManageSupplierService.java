package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.wrapper.SupplierWrapper;

public interface ManageSupplierService {

	List<SupplierWrapper> findAll();

	SupplierWrapper addSupplier(SupplierWrapper newSupplier);

	SupplierWrapper updateSupplier(SupplierWrapper existingSupplier);

	String deleteSupplier(SupplierWrapper existingSupplier);
}
