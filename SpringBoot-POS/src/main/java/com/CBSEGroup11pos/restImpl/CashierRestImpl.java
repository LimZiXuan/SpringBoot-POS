package com.CBSEGroup11pos.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.entity.Cashier;
import com.CBSEGroup11pos.rest.CashierRest;
import com.CBSEGroup11pos.service.CashierService;

@RestController
public class CashierRestImpl implements CashierRest {
	
	@Autowired
	private CashierService cashierService;
	
	@Override
	public ResponseEntity<Cashier> viewCashierInfo(String cashierId) {
		try {
			Cashier fetchedCashier = cashierService.viewCashierInfo(cashierId);
			return new ResponseEntity<Cashier>(fetchedCashier,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
    public ResponseEntity<Cashier> addCashier(Map<String, String> requestMap) {
        try {
            // Extracting values from the requestMap
            String name = requestMap.get("name");
            Integer age = Integer.parseInt(requestMap.get("age"));
            String gender = requestMap.get("gender");
            String address = requestMap.get("address");
            String phone = requestMap.get("phone");
            String email = requestMap.get("email");
            String password = requestMap.get("password");
            String dateCreated = requestMap.get("datecreated");

            // Creating a Customer object
            Cashier cashier = new Cashier();
            cashier.setName(name);
            cashier.setAge(age);
            cashier.setGender(gender);
            cashier.setAddress(address);
            cashier.setPhone(phone);
            cashier.setEmail(email);
            cashier.setPassword(password);
            cashier.setDatecreated(dateCreated);

            // Adding the customer using the service
            Cashier newCashier =  cashierService.addCashier(cashier);

            return new ResponseEntity<Cashier>(newCashier, HttpStatus.CREATED);
        } catch (Exception e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@Override
	public ResponseEntity<String> viewSalesHistory(String cashierId) {
		try {
			return cashierService.viewSalesHistory(cashierId);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
