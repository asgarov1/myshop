package com.asgarov.shop.service;

import java.util.Optional;

import com.asgarov.shop.entity.Admin;
import com.asgarov.shop.entity.User;
import com.asgarov.shop.repository.AdminRepository;
import com.asgarov.shop.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public CustomerDetailsService(
            final UserRepository userRepository,
            final AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        Optional<Admin> adminOptional = adminRepository.findByEmail(username);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else if (adminOptional.isPresent()) {
            return adminOptional.get();
        }

        throw new UsernameNotFoundException("{error.emailNotFound}");
    }
}
