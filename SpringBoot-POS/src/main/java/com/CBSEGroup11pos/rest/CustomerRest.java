package com.CBSEGroup11pos.rest;

import java.util.Map;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

@RequestMapping(path = "/customer")
public interface CustomerRest {
    @GetMapping(path = "/customers")
    ResponseEntity<List<CustomerWrapper>> getAllCustomers();

    @GetMapping(path = "/{id}")
    ResponseEntity<CustomerWrapper> getCustomerById(@PathVariable Integer id);

    @PostMapping(path = "/add")
    ResponseEntity<CustomerWrapper> addCustomer(@RequestBody CustomerWrapper customer);

    @PutMapping(path = "/update/{id}")
    ResponseEntity<CustomerWrapper> updateCustomer(@PathVariable Integer id,
            @RequestBody CustomerWrapper updatedCustomer);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteCustomer(@PathVariable Integer id);

}
