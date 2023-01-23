package com.golamrabbiazad.primaxstorebackend.repository;

import com.golamrabbiazad.primaxstorebackend.model.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, String> {
}
