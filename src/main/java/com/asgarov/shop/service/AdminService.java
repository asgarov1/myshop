package com.asgarov.shop.service;

import java.util.List;
import java.util.Optional;

import com.asgarov.shop.entity.Admin;
import com.asgarov.shop.repository.AdminRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    public AdminService(
            final AdminRepository adminRepository,
            final PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Admin admin) {
        encodePassword(admin);
        adminRepository.save(admin);
    }

    private void encodePassword(final Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findByEmail(final String email) {
        return adminRepository.findByEmail(email);
    }
}
