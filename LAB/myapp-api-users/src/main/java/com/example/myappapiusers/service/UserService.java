package com.example.myappapiusers.service;

import com.example.myappapiusers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String email);
}
