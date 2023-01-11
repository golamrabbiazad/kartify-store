//package com.golamrabbiazad.primaxstorebackend.model.feature;
//
//import com.golamrabbiazad.primaxstorebackend.model.Admin;
//import com.golamrabbiazad.primaxstorebackend.model.Order;
//import jakarta.persistence.*;
//
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "delivery")
//public class Delivery {
//    @Id
//    private String id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "admin_id")
//    private Admin admin;
//
//    @OneToMany(mappedBy = "delivery")
//    private List<Order> orders;
//
//    @Column(name = "delivered_at", nullable = false)
//    private Timestamp deliveredAt;
//
//    public Delivery() {
//        // Default constructor
//    }
//
//    public Delivery(String id, Admin admin, List<Order> orders, Timestamp deliveredAt) {
//        this.id = id;
//        this.admin = admin;
//        this.orders = orders;
//        this.deliveredAt = deliveredAt;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
//
//    public Timestamp getDeliveredAt() {
//        return deliveredAt;
//    }
//
//    public void setDeliveredAt(Timestamp deliveredAt) {
//        this.deliveredAt = deliveredAt;
//    }
//}
