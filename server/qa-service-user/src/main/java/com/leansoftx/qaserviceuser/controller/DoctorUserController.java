package com.leansoftx.qaserviceuser.controller;

import com.leansoftx.qaserviceuser.model.DoctorUser;
import com.leansoftx.qaserviceuser.service.DoctorUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorUserController {

    private final DoctorUserService doctorUserService;

    public DoctorUserController(DoctorUserService doctorUserService) {
        this.doctorUserService = doctorUserService;
    }

    @GetMapping
    public List<DoctorUser> getAllDoctors() {
        return doctorUserService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorUser> getDoctorById(@PathVariable String id) {
        Optional<DoctorUser> doctorUser = doctorUserService.getDoctorById(id);
        return doctorUser.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DoctorUser saveDoctor(@RequestBody DoctorUser doctorUser) {
        return doctorUserService.saveDoctor(doctorUser);
    }

    @PutMapping("/{id}")
    public DoctorUser updateDoctor(@PathVariable String id, @RequestBody DoctorUser doctorUser) {
        return doctorUserService.updateDoctor(id, doctorUser);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id) {
        doctorUserService.deleteDoctor(id);
    }
}