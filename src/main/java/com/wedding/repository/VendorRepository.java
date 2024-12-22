package com.wedding.repository;

import com.wedding.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByServiceTypeAndAvailable(String serviceType, boolean available);
}