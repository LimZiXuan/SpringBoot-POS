package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;

public interface CustomerService {
    List<CustomerWrapper> findAll();

    // Customer getCustomerById(String id);

    // Customer addCustomer(Customer customer);

    // Customer updateCustomer(String id, Customer updatedCustomer);

    // void deleteCustomer(String id);
}