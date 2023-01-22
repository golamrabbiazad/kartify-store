package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Customer;
import com.golamrabbiazad.primaxstorebackend.model.dto.LoginUser;
import com.golamrabbiazad.primaxstorebackend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer getCustomer(@RequestBody @Valid Customer customer) {
        customerService.createCustomer(customer);

        log.info("customer created!");
        return customer;
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody @Valid LoginUser user) {
        var customer = customerService.getCustomerByEmailAndPassword(user);
        return ResponseEntity.ok(customer);
    }
}
