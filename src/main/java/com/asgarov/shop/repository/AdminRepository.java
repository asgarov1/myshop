package com.asgarov.shop.repository;

import java.util.List;
import java.util.Optional;

import com.asgarov.shop.entity.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByEmail(String username);
    List<Admin> findAll();
}
