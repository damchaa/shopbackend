package com.example.backend.service;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.repo.RoleRepo;
import com.example.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User saveUser(User userEntity) {
        Role userRole = roleRepo.findByName("ROLE_USER");
        userEntity.setRole(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepo.save(userEntity);
    }
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public User findByUsernameAndPassword(String username,String password){
        User user = findByUsername(username);
        if (user != null){
            if (passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
