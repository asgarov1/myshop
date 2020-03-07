package com.asgarov.shop.service;

import java.util.Optional;

import com.asgarov.shop.entity.Admin;
import com.asgarov.shop.entity.Person;
import com.asgarov.shop.entity.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
    private UserService userService;
    private AdminService adminService;

    public PersonService(final UserService userService, final AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    public Person findUser(String email){
        Optional<User> studentOptional = userService.findByEmail(email);
        Optional<Admin> professorOptional = adminService.findByEmail(email);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else if (professorOptional.isPresent()) {
            return professorOptional.get();
        }

        throw new UsernameNotFoundException("Username not found!");
    }
}
