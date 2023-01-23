package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.entity.Customer;
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
    public ResponseEntity<Object> getCustomer(@RequestBody @Valid Customer customer) {
        var response = customerService.createCustomer(customer);
        if (response != null) {
            return new ResponseEntity<>(response.email() + " already exists!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginUser user) {
        var customer = customerService.getCustomerByEmailAndPassword(user);

        if (customer == null) {
            return new ResponseEntity<>(user.email() + " not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.FOUND);
    }
}
