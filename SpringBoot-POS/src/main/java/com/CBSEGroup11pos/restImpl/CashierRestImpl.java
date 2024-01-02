package com.CBSEGroup11pos.restImpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public ResponseEntity<Map<String, Object>> getAllCashier() {

		Map<String, Object> response = cashierService.getAllCashier();
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getCashier(String cashierId) {

		Map<String, Object> response = cashierService.getCashier(cashierId);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> addCashier(Map<String, String> requestMap) {

		// Adding the cashier using the service
		Map<String, Object> response = cashierService.addCashier(requestMap);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateCashier(String cashierId, Map<String, String> requestMap) {

		Map<String, Object> response = cashierService.updateCashier(cashierId, requestMap);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteCashier(String cashierId) {

		Map<String, Object> response = cashierService.deleteCashier(cashierId);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> viewSalesHistory(String cashierId) {

		Map<String, Object> response = cashierService.viewSalesHistory(cashierId);
		HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(response, status);
	}

	@Override
	public ResponseEntity<byte[]> viewTransactionGraph(String cashierId) {

		Map<String, Object> response = cashierService.viewTransactionGraph(cashierId);

		if ((boolean) response.get("success")) {
			byte[] chartImage = (byte[]) response.get("chartImage");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type

			return new ResponseEntity<>(chartImage, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
