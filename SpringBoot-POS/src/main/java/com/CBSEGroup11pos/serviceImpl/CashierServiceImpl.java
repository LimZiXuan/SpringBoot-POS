package com.CBSEGroup11pos.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.service.CashierService;

@Service
public class CashierServiceImpl implements CashierService{

	@Override
	public ResponseEntity<String> viewSalesHistory(Long cashierId) {
		try {
//			Business Logic here 
			String str = "Success " + cashierId;
			return new ResponseEntity<String>(str, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
