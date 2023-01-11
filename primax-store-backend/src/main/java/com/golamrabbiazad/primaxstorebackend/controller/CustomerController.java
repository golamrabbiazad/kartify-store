package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Customer;
import com.golamrabbiazad.primaxstorebackend.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    public Customer getCustomer (@RequestBody Customer customer) {
        customerService.createCustomer(customer);

        return customer;
    }
}
