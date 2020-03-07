package com.asgarov.shop.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.asgarov.shop.entity.Product;
import com.asgarov.shop.repository.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product find(Long id){
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByNameContainingIgnoreCase(final String searchInput) {
        return productRepository.findByNameContainingIgnoreCase(searchInput);
    }

    public void saveAll(final List<Product> products) {
        productRepository.saveAll(products);
    }
}
