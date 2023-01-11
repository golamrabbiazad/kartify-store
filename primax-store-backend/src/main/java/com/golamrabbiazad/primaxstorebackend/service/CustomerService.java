package com.golamrabbiazad.primaxstorebackend.service;

import com.golamrabbiazad.primaxstorebackend.exception.CustomerNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Customer;
import com.golamrabbiazad.primaxstorebackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
