package com.asgarov.shop.service;

import java.util.List;
import java.util.Optional;

import com.asgarov.shop.entity.User;
import com.asgarov.shop.repository.UserRepository;
import com.asgarov.shop.util.UserForm;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(
            final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    public void save(UserForm userForm) {
        User user = new User(
                userForm.getFirstName(), userForm.getLastName(), userForm.getEmail(), userForm.getPassword(),
                userForm.getAddress(), userForm.getCity(), userForm.getPostalCode(), userForm.getCountry());

        encodePassword(user);
        userRepository.save(user);
    }

    private void encodePassword(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByLastName(final String searchParameter) {
        return userRepository.findAllByLastNameContainingIgnoreCase(searchParameter);
    }

    public List<User> findAllByEmail(final String searchParameter) {
        return userRepository.findAllByEmailContainingIgnoreCase(searchParameter);
    }

    public List<User> findAllByPhone(final String searchParameter) {
        return userRepository.findAllByPhoneNumberContainingIgnoreCase(searchParameter);
    }

    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }
}
