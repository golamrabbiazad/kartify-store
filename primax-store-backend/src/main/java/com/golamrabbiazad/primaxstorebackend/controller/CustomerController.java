package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.entity.Customer;
import com.golamrabbiazad.primaxstorebackend.model.dto.LoginUser;
import com.golamrabbiazad.primaxstorebackend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> getCustomer(@Valid @RequestBody Customer customer) {
        var response = customerService.createCustomer(customer);
        if (response != null) {
            return new ResponseEntity<>(response.email() + " already exists!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody @Valid LoginUser user) {
        var customer = customerService.getCustomerByEmailAndPassword(user);

        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }
}
