package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.SupplierDao;
import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.service.ManageSupplierService;
import com.CBSEGroup11pos.util.SupplierTransformer;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@Service
public class ManageSupplierServiceImpl implements ManageSupplierService {

	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private SupplierTransformer supplierTransformer;

	@Override
	public List<SupplierWrapper> findAll() {
		List<SupplierWrapper> allSuppliers = new ArrayList<>();
		List<Supplier> entitySuppliers = supplierDao.findAll();
		for (Supplier data : entitySuppliers) {
			allSuppliers.add(supplierTransformer.transformEntityToObj(data));
		}
		return allSuppliers;
	}

	@Override
	public SupplierWrapper addSupplier(SupplierWrapper newSupplier) {
		Supplier entity = new Supplier();
		entity = supplierTransformer.transformObjToEntity(newSupplier);
		supplierDao.save(entity);
		return newSupplier;
	}

	@Override
	public SupplierWrapper updateSupplier(SupplierWrapper existingSupplier) {
		Supplier existingEntity = supplierDao.findById(existingSupplier.getId()).get();
		if (existingEntity != null) {
			existingEntity.setCompanyName(existingSupplier.getCompanyName());
			existingEntity.setLastDateSupplied(existingSupplier.getLastdateSupplied());
			supplierDao.save(existingEntity);
			return existingSupplier;
		}
		return null;
	}

	@Override
	public String deleteSupplier(SupplierWrapper existingSupplier) {
		Supplier existingEntity = supplierDao.findById(existingSupplier.getId()).get();
		if (existingEntity != null) {
			supplierDao.deleteById(existingSupplier.getId());
			return "Deleted Successfully";
		}
		return "Delete Failed";

	}

}
