package com.golamrabbiazad.primaxstorebackend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@JsonIgnore))
@Builder
public class Product {
    @Id
    private String productId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Min(value = 1, message = "price should be greater than 1")
    private Double price;

    @Column(nullable = false)
    @Min(value = 1, message = "quantity should be greater than 1")
    private Integer quantity;

    private String image;

    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
