package com.asgarov.shop.repository;

import java.util.List;
import java.util.Optional;

import com.asgarov.shop.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByLastNameContainingIgnoreCase(String searchParameter);

    List<User> findAllByEmailContainingIgnoreCase(String searchParameter);

    List<User> findAllByPhoneNumberContainingIgnoreCase(String searchParameter);
}
