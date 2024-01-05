package com.CBSEGroup11pos.restImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.ManageSupplierRest;
import com.CBSEGroup11pos.service.ManageSupplierService;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RestController
public class ManageSupplierRestImpl implements ManageSupplierRest {

	@Autowired
	private ManageSupplierService supplierService;

	@Override
	public ResponseEntity<List<SupplierWrapper>> getAllSupplier() {
		return ResponseEntity.ok(supplierService.findAll());
	}

	@Override
	public ResponseEntity<SupplierWrapper> addSupplier(SupplierWrapper newSupplier) {
		return ResponseEntity.ok(supplierService.addSupplier(newSupplier));
	}

	@Override
	public ResponseEntity<SupplierWrapper> updateSupplier(Integer supplierId, SupplierWrapper existingSupplier) {
		return ResponseEntity.ok(supplierService.updateSupplier(supplierId, existingSupplier));
	}

	@Override
	public ResponseEntity<String> deleteSupplier(Integer supplierId) {
		return ResponseEntity.ok(supplierService.deleteSupplier(supplierId));
	}

}
