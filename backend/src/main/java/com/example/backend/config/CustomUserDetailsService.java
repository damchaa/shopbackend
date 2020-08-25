package com.example.backend.config;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return CustomUserDetails.fromUserCustomUserDetails(user);
    }
}
