package com.golamrabbiazad.primaxstorebackend.repository;

import com.golamrabbiazad.primaxstorebackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, String> {
}
