package com.asgarov.shop.controller;

import java.util.Map;

import com.asgarov.shop.entity.Cart;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private ProductService productService;
    private Cart shoppingCart;

    public ProductController(final ProductService productService, final Cart shoppingCart) {
        this.productService = productService;
        this.shoppingCart = shoppingCart;
    }

    @RequestMapping("showProduct")
    public String showProduct(@RequestParam Long id, Model model) {
        Product product = productService.find(id);
        model.addAttribute("product", product);
        return "product";
    }

    @ModelAttribute("productsInCart")
    public Map<Product, Integer> productsInCart() {
        return shoppingCart.getProductsInCart();
    }

    @ModelAttribute("total")
    public Integer total() {
        return shoppingCart.calculateTotal();
    }

}
