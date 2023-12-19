package com.CBSEGroup11pos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CashierDao;
import com.CBSEGroup11pos.entity.Cashier;
import com.CBSEGroup11pos.service.CashierService;

@Service
public class CashierServiceImpl implements CashierService{
	
	@Autowired
	CashierDao cashierDao;
	
	@Override
	public ResponseEntity<Cashier> viewCashierInfo(String cashierId) {
		try {
//			Business Logic here 
			Cashier cashier = cashierDao.findById(cashierId).get();
			return new ResponseEntity<Cashier>(cashier, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> viewSalesHistory(String cashierId) {
		try {
//			Business Logic here 
			String str = "Success " + cashierId;
			return new ResponseEntity<String>(str, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
