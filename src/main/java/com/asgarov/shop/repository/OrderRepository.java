package com.asgarov.shop.repository;

import java.util.List;

import com.asgarov.shop.entity.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
