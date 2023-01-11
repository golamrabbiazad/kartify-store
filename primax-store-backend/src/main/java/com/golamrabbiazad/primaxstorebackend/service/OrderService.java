package com.golamrabbiazad.primaxstorebackend.service;

import com.golamrabbiazad.primaxstorebackend.exception.OrderNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Order;
import com.golamrabbiazad.primaxstorebackend.model.OrderStatus;
import com.golamrabbiazad.primaxstorebackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) throws OrderNotFoundException {
        var order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order with " + orderId + " not found.");
        }

        return order.get();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order approveOrder(String orderId) throws OrderNotFoundException {
        var oldOrder = orderRepository.findById(orderId);
        if (oldOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with " + orderId + " not found.");
        }

        var newOrder = oldOrder.get();
        newOrder.setStatus(OrderStatus.APPROVED);
        newOrder.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(newOrder);
    }

    public void rejectOrder(String orderId) throws OrderNotFoundException {
        var getOrder = orderRepository.findById(orderId);
        if (getOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with " + orderId + " not found.");
        }

        var order = getOrder.get();

        orderRepository.delete(order);
    }
}
