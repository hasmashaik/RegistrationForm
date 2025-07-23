package com.ecom.repository;


import com.ecom.model.Product;
import com.ecom.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(Seller seller);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory(String category); // fixed
}
/* is responsible for handling database operations for the Product entity.
These methods help you filter and display products efficiently in your application.
No SQL is written manually — Spring builds the queries just from method names.
Helps implement features like search bar, filter by seller, filter by category.

*/