package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{
	Product findById();
}
