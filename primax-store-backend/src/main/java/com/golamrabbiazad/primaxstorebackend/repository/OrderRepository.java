package com.golamrabbiazad.primaxstorebackend.repository;

import com.golamrabbiazad.primaxstorebackend.model.Order;
import com.golamrabbiazad.primaxstorebackend.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByStatus(OrderStatus delivered);
}
