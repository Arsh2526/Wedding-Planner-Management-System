package com.wedding.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private Boolean available = true;

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

