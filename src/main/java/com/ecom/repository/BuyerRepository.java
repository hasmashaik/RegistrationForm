package com.ecom.repository;



import com.ecom.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	
}
/*
 * BuyerRepository is a Spring Data JPA Repository interface that provides database access methods for the Buyer entity.

By extending JpaRepository, it automatically provides CRUD operations for the Buyer class without writing SQL.


 */