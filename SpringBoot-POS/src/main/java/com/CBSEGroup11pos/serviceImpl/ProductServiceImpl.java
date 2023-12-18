package com.CBSEGroup11pos.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductDao;
import com.CBSEGroup11pos.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
//	@Autowired
//	ProductDao dao;

	@Override
	public ResponseEntity<String> addProduct(String requestMap) {
		try {
//			Business Logic here 
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
