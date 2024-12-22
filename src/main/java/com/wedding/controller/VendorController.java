package com.wedding.controller;

import com.wedding.entity.Vendor;
import com.wedding.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Register a new vendor
    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        return ResponseEntity.ok(createdVendor);
    }

    // Update vendor availability
    @PutMapping("/{id}/updateAvailability")
    public ResponseEntity<Vendor> updateAvailability(@PathVariable Long id, @RequestParam Boolean availability) {
        Vendor updatedVendor = vendorService.updateVendorAvailability(id, availability);
        return ResponseEntity.ok(updatedVendor);
    }

    // Get all vendors
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    // Get available vendors by service type
    @GetMapping("/available")
    public ResponseEntity<List<Vendor>> getAvailableVendors(@RequestParam String serviceType) {
        List<Vendor> availableVendors = vendorService.getAvailableVendors(serviceType);
        return ResponseEntity.ok(availableVendors);
    }
}

