package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;

public interface CustomerService {
    List<CustomerWrapper> findAll();

    CustomerWrapper getCustomerById(Integer id);

    CustomerWrapper addCustomer(CustomerWrapper customer);

    CustomerWrapper updateCustomer(Integer id, CustomerWrapper updatedCustomer);

    String deleteCustomer(Integer id);

}