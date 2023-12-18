package dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Customer;
public interface CustomerDao extends JpaRepository<Customer, String> { 
	List<Customer> findAll();
}
