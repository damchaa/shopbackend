package com.example.backend.controller;

import com.example.backend.model.Product;

import java.util.Set;

public class OrderRequest {
    private String token;
    private Set<Product> orders;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Product> getOrders() {
        return orders;
    }

    public void setOrders(Set<Product> orders) {
        this.orders = orders;
    }
    public String toString(){
        return "Request" + token;
    }
}