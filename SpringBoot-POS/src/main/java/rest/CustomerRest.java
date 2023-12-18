package rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.CustomerRest;
import service.CustomerService;
@RequestMapping(path = "/customer")
public interface CustomerRest {
	
	@PostMapping(path = "/addCustomer")
	ResponseEntity<String> addCustomer(@RequestBody Map<String, String> requestMap);
}
