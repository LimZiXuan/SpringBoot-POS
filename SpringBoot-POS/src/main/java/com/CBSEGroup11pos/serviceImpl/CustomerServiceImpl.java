package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CustomerDao;
import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.service.CustomerService;
import com.CBSEGroup11pos.util.CustomerTransformer;
import com.CBSEGroup11pos.util.ProductItemTransformer;
import com.CBSEGroup11pos.wrapper.CardWrapper;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.SupplierWrapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerTransformer customerTransformer;

    @Override
    public List<CustomerWrapper> findAll() {
        List<CustomerWrapper> allCustomers = new ArrayList<CustomerWrapper>();
        List<Customer> entityCustomers = customerDao.findAll();
        for (Customer data : entityCustomers) {
            allCustomers.add(customerTransformer.transformEntityToObj(data));
        }
        return allCustomers;
    }

    @Override
    public CustomerWrapper getCustomerById(Integer id) {
        CustomerWrapper customer = customerTransformer
                .transformEntityToObj(customerDao.findById(id).orElseThrow(() -> new EntityNotFoundException()));
        return customer;
    }

    @Override
    public CustomerWrapper addCustomer(CustomerWrapper customer) {
        CustomerWrapper customerWrapper = customerTransformer
                .transformEntityToObj(customerDao.save(customerTransformer.transformObjToEntity(customer)));
        return customerWrapper;
    }

    @Override
    public CustomerWrapper updateCustomer(Integer id, CustomerWrapper updatedCustomer) {
        Customer existingEntity = customerDao.findById(id).get();
        if (existingEntity != null) {
            existingEntity.setName(updatedCustomer.getName());
            existingEntity.setEmail(updatedCustomer.getEmail());
            customerDao.save(existingEntity);
            return updatedCustomer;
        }
        return null;
    }

    @Override
    public String deleteCustomer(Integer id) {
        customerDao.deleteById(id);
        return "Deleted Successfully";

    }
}
