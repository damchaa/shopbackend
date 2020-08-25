package com.example.backend.controller;

import com.example.backend.model.Order;
import com.example.backend.model.Product;
import com.example.backend.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
    @GetMapping("/orders/{id}")
    public Set<Product> getOrderById(@PathVariable(value = "id") Integer id){

        Set<Product> products = orderRepo.findById(id).get().getProducts();
        return products;
    }
}
