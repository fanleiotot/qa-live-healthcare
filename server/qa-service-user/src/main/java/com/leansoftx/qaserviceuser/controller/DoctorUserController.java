package com.leansoftx.qaserviceuser.controller;

import com.leansoftx.qaserviceuser.model.Doctor;
import com.leansoftx.qaserviceuser.service.DoctorUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorUserController {

    private final DoctorUserService doctorUserService;

    public DoctorUserController(DoctorUserService doctorUserService) {
        this.doctorUserService = doctorUserService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorUserService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable String id) {
        return doctorUserService.getDoctorById(id);
    }

    @GetMapping("/active")
    public List<Doctor> getActiveDoctors() {
        return doctorUserService.getActiveDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorUserService.addDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        return doctorUserService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id) {
        doctorUserService.deleteDoctor(id);
    }
}