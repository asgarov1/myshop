package com.asgarov.shop.repository;

import java.util.List;

import com.asgarov.shop.entity.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findAll();
}
