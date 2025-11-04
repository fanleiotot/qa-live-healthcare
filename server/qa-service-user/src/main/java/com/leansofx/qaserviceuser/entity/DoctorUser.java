package com.leansofx.qaserviceuser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "doctor_user")
@Data
public class DoctorUser {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String experience;

    @ElementCollection
    @CollectionTable(name = "doctor_specialties", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "specialty")
    private List<String> specialties;

    @Column(nullable = false)
    private boolean isActive;
}