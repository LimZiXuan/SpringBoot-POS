package com.CBSEGroup11pos.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.CustomerRest;
import com.CBSEGroup11pos.service.CustomerService;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;

@RestController
public class CustomerRestImpl implements CustomerRest {

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerWrapper>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @Override
    public ResponseEntity<CustomerWrapper> getCustomerById(Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Override
    public ResponseEntity<CustomerWrapper> addCustomer(CustomerWrapper customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @Override
    public ResponseEntity<CustomerWrapper> updateCustomer(Integer id, CustomerWrapper updatedCustomer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
