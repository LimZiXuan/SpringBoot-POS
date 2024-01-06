package com.CBSEGroup11pos.util;

import org.springframework.stereotype.Component;

import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.wrapper.CustomerWrapper;

@Component

public class CustomerTransformer {
    public CustomerWrapper transformEntityToObj(Customer entity) {
        CustomerWrapper obj = new CustomerWrapper();
        obj.setId(entity.getId());
        obj.setName(entity.getName());
        obj.setAge(entity.getAge());
        obj.setAddress(entity.getAddress());
        obj.setGender(entity.getGender());
        obj.setPhone(entity.getPhone());
        obj.setEmail(entity.getEmail());
        return obj;
    }

    public Customer transformObjToEntity(CustomerWrapper obj) {
        Customer entity = new Customer();
        entity.setId(obj.getId());
        entity.setAddress(obj.getAddress());
        entity.setAge(obj.getAge());
        entity.setEmail(obj.getEmail());
        entity.setName(obj.getName());

        entity.setGender(obj.getGender());
        entity.setPhone(obj.getPhone());
        return entity;
    }
}