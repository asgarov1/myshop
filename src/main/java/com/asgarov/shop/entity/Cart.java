package com.asgarov.shop.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Scope("session")
public class Cart {

    @ElementCollection
    private Map<Product, Integer> productsInCart = new HashMap<>();

    public void clear() {
        productsInCart.clear();
    }

    public void addProduct(Product product) {
        productsInCart.putIfAbsent(product, 1);
        productsInCart.computeIfPresent(product, (key, value) -> ++value);
    }

    public void addProduct(Product product, int amount) {
        productsInCart.putIfAbsent(product, amount);
        productsInCart.computeIfPresent(product, (key, value) -> (value+amount));
    }

    public void subtractProduct(final Product product) {
        productsInCart.computeIfPresent(product, (key, value) -> --value);
        if(productsInCart.get(product) == 0) {
            productsInCart.remove(product);
        }
    }

    public void removeProduct(Product product) {
        productsInCart.remove(product);
    }

    public Integer calculateTotal() {
        return productsInCart.isEmpty() ?
                0 : productsInCart.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .mapToInt(x -> x)
                .sum();
    }
}
