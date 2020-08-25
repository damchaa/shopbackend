package com.example.backend.controller;

import com.example.backend.config.jwt.JwtProvider;
import com.example.backend.model.Order;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.repo.OrderRepo;
import com.example.backend.repo.ProductRepo;
import com.example.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/api/user/product")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }




    @PostMapping("/api/user/product")
    public Integer createOrder(@RequestBody OrderRequest orderRequest){
        Order order = new Order();
        String login = jwtProvider.getLoginFromToken(orderRequest.getToken());
        System.out.println(login);
        User user = userRepo.findByUsername(login);
        order.setUser(user);

        order.setProducts(orderRequest.getOrders());
        orderRepo.save(order);
        Integer orderNumber = orderRepo.findAll().size();

        return orderNumber;
    }

}
