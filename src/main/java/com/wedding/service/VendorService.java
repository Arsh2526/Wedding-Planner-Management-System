package com.wedding.service;

import com.wedding.entity.Vendor;
import com.wedding.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // Register a new vendor
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Update the availability of a vendor
    public Vendor updateVendorAvailability(Long id, Boolean availability) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorOptional.get();
            vendor.setAvailable(availability);
            return vendorRepository.save(vendor);
        } else {
            throw new RuntimeException("Vendor not found with ID: " + id);
        }
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Get available vendors by service type
    public List<Vendor> getAvailableVendors(String serviceType) {
        return vendorRepository.findByServiceTypeAndAvailable(serviceType, true);
    }
}

