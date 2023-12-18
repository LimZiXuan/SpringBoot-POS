package service;
import dao.CustomerDao;
import entity.Customer;

import java.util.List;
public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    Customer addCustomer(Customer customer);

    Customer updateCustomer(String id, Customer updatedCustomer);

    void deleteCustomer(String id);
}
