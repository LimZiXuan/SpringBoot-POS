package restImpl;

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

import entity.Customer;
import rest.CustomerRest;
import service.CustomerService;

@RestController
public class CustomerRestImpl implements CustomerRest {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return customers;
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or return an error response)
            e.printStackTrace();
            return null;
        }
    } 
    @Override
    @PostMapping("/customer/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Map<String, String> requestMap) {
        try {
            // Extracting values from the requestMap
            String name = requestMap.get("name");
            Integer age = Integer.parseInt(requestMap.get("age"));
            String gender = requestMap.get("gender");
            String address = requestMap.get("address");
            String phone = requestMap.get("phone");
            String email = requestMap.get("email");

            // Creating a Customer object
            Customer customer = new Customer();
            customer.setName(name);
            customer.setAge(age);
            customer.setGender(gender);
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setEmail(email);

            // Adding the customer using the service
            customerService.addCustomer(customer);

            return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}