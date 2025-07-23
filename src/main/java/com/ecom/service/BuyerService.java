package com.ecom.service;

import com.ecom.model.*;
import com.ecom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;
    
    @Autowired
    private UserService userService;
    
    public Buyer registerBuyer(Buyer buyer) {
        userService.registerUser(buyer, UserType.BUYER);
        return buyerRepository.save(buyer);
    }
    
    public Buyer updateBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
    
    public Buyer findByEmail(String email) {
        User user = userService.findByEmail(email);
        if (user != null && user.getUserType().equals(UserType.BUYER)) {
            return buyerRepository.findById(user.getId()).orElse(null);
        }
        return null;
    }

}

/*buyerRepository: Used to interact with the buyers table (database).
userService: Common logic related to the parent User class (which Buyer extends).

The BuyerService class contains business logic related to Buyer users. It connects the controller layer with the database (via repository) and performs operations like registration, updating, and searching a buyer.


 * */
