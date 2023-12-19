package com.CBSEGroup11pos.restImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.CashierRest;
import com.CBSEGroup11pos.service.CashierService;

@RestController
public class CashierRestImpl implements CashierRest {
	
	@Autowired
	private CashierService cashierService;

	@Override
	public ResponseEntity<String> viewSalesHistory(Long cashierId) {
		try {
			return cashierService.viewSalesHistory(cashierId);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
