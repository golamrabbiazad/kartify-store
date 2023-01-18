package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.exception.OrderNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Order;
import com.golamrabbiazad.primaxstorebackend.service.CustomerService;
import com.golamrabbiazad.primaxstorebackend.service.OrderService;
import com.golamrabbiazad.primaxstorebackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;


    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) throws OrderNotFoundException {
        return orderService.getOrder(id);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) throws CustomerNotFoundException {
        // validate customer
        if (order.getCustomer() == null || order.getCustomer().getCustomerId() == null) {
            throw new IllegalArgumentException("Customer must be provided for creating order.");
        }
        // set customer
        order.setCustomer(customerService.getCustomerById(order.getCustomer().getCustomerId()));
        // set products
        order.setProducts(productService.getProductsByIds(productService.getAllProductIds()));

        return orderService.createOrder(order);
    }

    @PutMapping("/approve/{id}")
    public Order approveOrder(@PathVariable String id) throws OrderNotFoundException {
        return orderService.approveOrder(id);
    }

    @DeleteMapping("/reject/{id}")
    public void rejectOrder(@PathVariable String id) throws OrderNotFoundException {
        orderService.rejectOrder(id);
    }
}
