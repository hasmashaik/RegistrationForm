package com.ecom.repository;


import com.ecom.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}


/*
 * handles data access for the Seller entity.

 * */
 