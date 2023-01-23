package com.golamrabbiazad.primaxstorebackend.repository;

import com.golamrabbiazad.primaxstorebackend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findCustomerByEmailAndPassword(String email, String password);

    Customer findCustomerByEmail(String email);
}
