package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.example.backend.config.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/register")
    public String registerUser(@RequestBody @Validated RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getUsername());
        userService.saveUser(u);
        return "OK";
    }
    @PostMapping("/login")
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        User user = userService.findByUsernameAndPassword(authRequest.getUsername(),authRequest.getPassword());
        String role = userService.findByUsername(authRequest.getUsername()).getRole().getName();
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token,role);
    }
}
