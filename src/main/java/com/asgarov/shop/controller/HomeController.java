package com.asgarov.shop.controller;

import java.util.List;
import java.util.Map;

import com.asgarov.shop.entity.Cart;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.service.PersonService;
import com.asgarov.shop.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private ProductService productService;
    private Cart shoppingCart;

    public HomeController(final ProductService productService, final Cart shoppingCart) {
        this.productService = productService;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping({"/", "", "/index"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchInput, Model model) {
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("products", productService.findByNameContainingIgnoreCase(searchInput));
        return "searchResults";
    }

    @ModelAttribute("productsInCart")
    public Map<Product, Integer> productsInCart() {
        return shoppingCart.getProductsInCart();
    }

    @ModelAttribute("total")
    public Integer total() {
        return shoppingCart.calculateTotal();
    }

    @ModelAttribute("products")
    public List<Product> allProducts() {
        return productService.findAll();
    }

//    @ModelAttribute
//    public void currentUser(@AuthenticationPrincipal UserDetails currentUser, Model model) {
//        if(currentUser != null) {
//            Person user = personService.findUser(currentUser.getUsername());
//            model.addAttribute("user", user);
//        }
//    }

}
