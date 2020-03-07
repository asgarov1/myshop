package com.asgarov.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.asgarov.shop.entity.Cart;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.service.OrderService;
import com.asgarov.shop.service.ProductService;
import com.asgarov.shop.util.OrderForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private Cart shoppingCart;
    private ProductService productService;
    private OrderService orderService;

    public CartController(
            final Cart shoppingCart,
            final ProductService productService,
            final OrderService orderService) {
        this.shoppingCart = shoppingCart;
        this.productService = productService;
        this.orderService = orderService;
    }

    //    TODO: add confirmation?
    @GetMapping("addToCart/{id}/")
    public String addToCartWithAmount(@PathVariable Long id, @RequestParam Integer amount, HttpServletRequest request) {
        Product product = productService.find(id);
        shoppingCart.addProduct(product, amount);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("adjust/addToCart/{id}/")
    public String addToCart(@PathVariable Long id, HttpServletRequest request) {
        Product product = productService.find(id);
        shoppingCart.addProduct(product);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("adjust/subtractFromCart/{id}/")
    public String subtractFromCart(@PathVariable Long id, HttpServletRequest request) {
        Product product = productService.find(id);
        shoppingCart.subtractProduct(product);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/removeProduct")
    public String removeProduct(@RequestParam Long id, HttpServletRequest request) {
        Product product = productService.find(id);
        shoppingCart.removeProduct(product);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/showCart")
    public String showCart(){
        return "showCart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        return "checkout/checkout";
    }

    @PostMapping("/submitOrderAsGuest")
    public String submitOrderAsGuest(@Valid OrderForm orderForm, Model model){
        orderService.placeOrder(orderForm);
        model.addAttribute("orders", orderService.findAll());
        return "checkout/orderConfirmation";
    }

    @ModelAttribute("/productsInCart")
    public Map<Product, Integer> productsInCart() {
        return shoppingCart.getProductsInCart();
    }

    @ModelAttribute("/total")
    public Integer total() {
        return shoppingCart.calculateTotal();
    }
}
