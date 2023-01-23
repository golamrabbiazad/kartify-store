package com.golamrabbiazad.primaxstorebackend.service;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.entity.Customer;
import com.golamrabbiazad.primaxstorebackend.model.dto.EmailFoundResponse;
import com.golamrabbiazad.primaxstorebackend.model.dto.LoginUser;
import com.golamrabbiazad.primaxstorebackend.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }

        return customer.get();
    }

    public Customer getCustomerByEmailAndPassword(LoginUser user) throws CustomerNotFoundException {
        return customerRepository.findCustomerByEmailAndPassword(user.email(), user.password());
    }

    public EmailFoundResponse createCustomer(Customer customer) {
        var existsCustomer = customerRepository.findCustomerByEmail(customer.getEmail());

        if (existsCustomer != null) {
            log.info("customer already exists!");
            return new EmailFoundResponse(existsCustomer.getEmail());
        }

        customerRepository.save(customer);
        log.info("customer created!");
        return null;
    }
}
