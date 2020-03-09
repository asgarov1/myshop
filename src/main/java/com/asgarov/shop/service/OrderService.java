package com.asgarov.shop.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.asgarov.shop.entity.Cart;
import com.asgarov.shop.entity.Order;
import com.asgarov.shop.entity.OrderItem;
import com.asgarov.shop.repository.OrderRepository;
import com.asgarov.shop.util.OrderForm;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private Cart shoppingCart;

    public OrderService(final OrderRepository orderRepository, final Cart shoppingCart) {
        this.orderRepository = orderRepository;
        this.shoppingCart = shoppingCart;
    }

    public void placeOrder(final OrderForm orderForm) {
        orderRepository.save(createOrder(orderForm));
        shoppingCart.clear();
    }

    private Order createOrder(final OrderForm orderForm) {
        Order order = new Order();

        order.setFirstName(orderForm.getFirstName());
        order.setLastName(orderForm.getLastName());
        order.setEmail(orderForm.getEmail());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setCity(orderForm.getCity());
        order.setAddress(orderForm.getAddress());
        order.setCountry(orderForm.getCountry());
        order.setPostalCode(orderForm.getPostalCode());

        order.setOrderedProducts(shoppingCart.getProductsInCart().entrySet()
                .stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        return order;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllById(final String searchParameter) {
        try {
            Long id = Long.parseLong(searchParameter);
            return orderRepository.findAllByIdLike(id);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    public List<Order> findAllByDate(final String searchParameter) {
        return orderRepository.findAllByLocalDateContainingIgnoreCase(searchParameter);
    }

    public List<Order> findAllByLastName(final String searchParameter) {
        return orderRepository.findAllByLastNameContainingIgnoreCase(searchParameter);
    }

    public Set<Order> findAllByIdDateLastName(final String searchParameter) {
        Set<Order> orders = new HashSet<>();
        orders.addAll(findAllById(searchParameter));
        orders.addAll(findAllByDate(searchParameter));
        orders.addAll(findAllByLastName(searchParameter));
        return orders;
    }

    public void save(final Order order) {
        orderRepository.save(order);
    }
}
