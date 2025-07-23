package com.ecom.service;


import com.ecom.model.*;
import com.ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private EmailService emailService;
    
    public Order createOrder(Product product, Buyer buyer, int quantity) {
        Order order = new Order();
        order.setProduct(product);
        order.setBuyer(buyer);
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        order.setStatus("PENDING");
        order.setOrderDate(new Date());
        
        Order savedOrder = orderRepository.save(order);
        
        // Notify seller
        String sellerEmail = product.getSeller().getEmail();
        String emailText = "You have a new order for product: " + product.getName() + 
                          "\nQuantity: " + quantity + 
                          "\nTotal: $" + order.getTotalPrice() + 
                          "\nBuyer: " + buyer.getFirstName() + " " + buyer.getLastName();
        emailService.sendEmail(sellerEmail, "New Order Notification", emailText);
        
        return savedOrder;
    }
    
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            if (status.equals("DELIVERED")) {
                order.setDeliveryDate(new Date());
            }
            return orderRepository.save(order);
        }
        return null;
    }
    
    public List<Order> getOrdersByBuyer(Buyer buyer) {
        return orderRepository.findByBuyer(buyer);
    }
    
    public List<Order> getOrdersBySeller(Seller seller) {
        return orderRepository.findByProductSeller(seller);
    }
    
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

}

/*
 * This service handles all order-related business logic, such as:

Creating new orders

Updating order status

Sending order notifications

Retrieving orders for buyers or sellers
 * */
 