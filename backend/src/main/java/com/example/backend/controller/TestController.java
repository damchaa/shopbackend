package com.example.backend.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "/http://localhost:4200")
public class TestController {
    @GetMapping("/admin/get")
    public String getAdmin(){
        return "Hello admin!";
    }

    @GetMapping("/user/get")
    public String getUser(){
        return "Hello user";
    }
}
