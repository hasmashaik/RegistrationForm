package com.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Seller extends User {
    private String companyName;
    private String taxNumber;
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getTaxNumber() { return taxNumber; }
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

}
/*
This Seller class is a JPA entity representing the sellers table in your e-commerce application's database. It extends the User class, meaning it inherits common user-related fields like id, name, email, etc.
It defines additional fields specific to sellers (like company name and tax number), while inheriting general user fields from the User superclass.
*/