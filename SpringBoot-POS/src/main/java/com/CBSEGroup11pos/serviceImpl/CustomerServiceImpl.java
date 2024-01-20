package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CustomerDao;
import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.service.CustomerService;
import com.CBSEGroup11pos.util.CustomerTransformer;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;

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
            existingEntity.setAge(updatedCustomer.getAge());
            existingEntity.setAddress(updatedCustomer.getAddress());
            existingEntity.setGender(updatedCustomer.getGender());
            existingEntity.setPhone(updatedCustomer.getPhone());
            customerDao.save(existingEntity);
            return updatedCustomer;
        }
        return null;
    }

    @Override
    public String deleteCustomer(Integer id) {
        customerDao.deleteById(id);
        return "Customer Deleted Successfully";

    }
}
